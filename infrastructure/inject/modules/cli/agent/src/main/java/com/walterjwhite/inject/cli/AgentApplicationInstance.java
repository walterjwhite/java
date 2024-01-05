package com.walterjwhite.inject.cli;

import com.walterjwhite.file.modules.resources.JarListUtils;
import com.walterjwhite.file.modules.resources.JarReadUtils;
import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.infrastructure.inject.core.service.ServiceManager;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.modules.snakeyaml.Snakeyaml;
import com.walterjwhite.transform.ArgumentTransformer;
import com.walterjwhite.transform.annotation.Transformer;
import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.Set;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.reflections.Reflections;

// command-line, the remaining, unprocessed arguments are to be passed to the handler)
// removed because it isn't used currently and may impact dependencies
@Slf4j
@Getter
public class AgentApplicationInstance extends ApplicationInstance {
  protected final Instrumentation instrumentation;
  protected final String arguments;

  protected final transient AgentBuilder.Default agentBuilder = new AgentBuilder.Default();

  protected final transient SerializationService serializationService = new Snakeyaml();

  public AgentApplicationInstance(
      Reflections reflections,
      PropertyManager propertyManager,
      ServiceManager serviceManager,
      SecretService secretService,
      Injector injector,
      Instrumentation instrumentation,
      String arguments) {
    super(reflections, propertyManager, serviceManager, secretService, injector);
    this.arguments = arguments;
    this.instrumentation = instrumentation;
  }

  @Override
  protected void initialize() throws Exception {
    // Recall that the premain runs before main
    // The actual application instance will override this later
    super.initialize();

    try {
      for (final Class transformerClass : getTransformerClasses()) {
        initTransformer(instrumentation, transformerClass);
      }
    } catch (Throwable t) {
      LOGGER.error("Error running agent", t);
    }
  }

  protected Set<Class<?>> getTransformerClasses() {
    return reflections.getTypesAnnotatedWith(Transformer.class);
  }

  protected void initTransformer(
      final Instrumentation instrumentation, final Class transformerClass) {
    final Transformer transformer = (Transformer) transformerClass.getAnnotation(Transformer.class);

    try {
      final Method initMethod =
          transformerClass.getDeclaredMethod(
              transformer.method(),
              AgentBuilder.class,
              Instrumentation.class,
              SerializationService.class,
              transformer.configurationClass(),
              Method.class);
      initTransformerConfiguration(instrumentation, transformerClass, transformer, initMethod);
    } catch (NoSuchMethodException | IOException | URISyntaxException e) {
      throw new Error("Unable to initialize transformer", e);
    }
  }

  protected void initTransformerConfiguration(
      final Instrumentation instrumentation,
      final Class transformerClass,
      final Transformer transformer,
      final Method initMethod)
      throws IOException, URISyntaxException {
    try {
      for (final String resource : JarListUtils.getFiles(transformer.pattern())) {
        final TransformInstanceConfiguration configuration =
            serializationService.deserialize(
                JarReadUtils.getFileFromResourceAsStream(resource),
                transformer.configurationClass());
        final Method method =
            ArgumentTransformer.getMethod(
                configuration.getTargetClassName(), configuration.getTargetMethodName());

        try {
          initMethod.invoke(
              null, agentBuilder, instrumentation, serializationService, configuration, method);
        } catch (IllegalAccessException | InvocationTargetException e) {
          throw new Error("Unable to initialize transformer", e);
        }

        setupTransformer(agentBuilder, instrumentation, method, transformerClass);
      }
    } catch (NoSuchFileException e) {
      LOGGER.warn(String.format("no files found for: %s", transformer.pattern()), e);
    }
  }

  public static void setupTransformer(
      final AgentBuilder agentBuilder,
      final Instrumentation instrumentation,
      final Method method,
      final Class transformerClass) {
    agentBuilder
        .disableClassFormatChanges()
        .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
        .type(ElementMatchers.is(method.getDeclaringClass()))
        .transform(
            (builder, typeDescription, classLoader, module, protectionDomain) ->
                builder.visit(
                    Advice.to(transformerClass)
                        .on(
                            ElementMatchers.named(method.getName())
                                .and(ElementMatchers.isPublic()))))
        .installOn(instrumentation);
  }
}
