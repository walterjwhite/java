package com.walterjwhite.inject.cli;

import com.walterjwhite.file.modules.resources.JarListUtils;
import com.walterjwhite.file.modules.resources.JarReadUtils;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.transform.ArgumentTransformer;
import com.walterjwhite.transform.annotation.Transformer;
import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.Set;

@Slf4j
public class TransformerAgentHandler implements AgentHandler{
    @Override
    public void initialize(AgentApplicationInstance agentApplicationInstance) {
        final AgentBuilder agentBuilder = new AgentBuilder.Default();
        agentBuilder.installOn(agentApplicationInstance.getInstrumentation());

        try {
            for (final Class transformerClass : getTransformerClasses(agentApplicationInstance.getReflections())) {
                initTransformer(agentBuilder, agentApplicationInstance.getSerializationService(), agentApplicationInstance.getInstrumentation(), transformerClass);
            }
        } catch (Throwable t) {
            LOGGER.error("Error running agent", t);
        }
    }

    public static Set<Class<?>> getTransformerClasses(final Reflections reflections) {
        return reflections.getTypesAnnotatedWith(Transformer.class);
    }

    public static void initTransformer(
            final AgentBuilder agentBuilder,
            final SerializationService serializationService,
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
            initTransformerConfiguration(agentBuilder, serializationService, instrumentation, transformerClass, transformer, initMethod);
        } catch (NoSuchMethodException | IOException | URISyntaxException e) {
            throw new Error("Unable to initialize transformer", e);
        }
    }

    public static void initTransformerConfiguration(
            final AgentBuilder agentBuilder,
            final SerializationService serializationService,
            final Instrumentation instrumentation,
            final Class transformerClass,
            final Transformer transformer,
            final Method initMethod)
            throws IOException, URISyntaxException {
        try {
            for (final String resource : JarListUtils.getFiles(transformerClass, transformer.pattern())) {
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
