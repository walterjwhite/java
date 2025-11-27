package com.walterjwhite.infrastructure.inject.providers.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.multibindings.Multibinder;
import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.infrastructure.inject.core.service.ServiceManager;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.PropertyImpl;
import jakarta.inject.Provider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

@Slf4j
@RequiredArgsConstructor
public class GuiceApplicationPropertyRegistrationModule extends AbstractModule {
  protected final transient Map<Class, Multibinder> multibinderMap = new HashMap<>();

  protected final GuiceApplicationModule[] guiceApplicationModules;
  protected transient PropertyManager propertyManager;

  protected void configure() {
    propertyManager = ApplicationHelper.getApplicationInstance().getPropertyManager();

    bindCommon();
    bindProperties();

    autoInstallModules();
    autoBindImplementations();

    multibinderMap.clear();
  }

  protected void bindCommon() {
    bind(Reflections.class).toInstance(ApplicationHelper.getApplicationInstance().getReflections());
    bind(ApplicationInstance.class).toInstance(ApplicationHelper.getApplicationInstance());
    bind(PropertyManager.class)
        .toInstance(ApplicationHelper.getApplicationInstance().getPropertyManager());
    bind(ServiceManager.class)
        .toInstance(ApplicationHelper.getApplicationInstance().getServiceManager());
    bind(SecretService.class)
        .toInstance(ApplicationHelper.getApplicationInstance().getSecretService());
  }

  protected void bindProperties() {
    propertyManager.getKeys().forEach(configurableProperty -> bindToProperty(configurableProperty));
  }

  public void bindToProperty(Class<? extends ConfigurableProperty> configurablePropertyClass) {
    final String value = propertyManager.get(configurablePropertyClass);

    if (value == null) {

      return;
    }

    bindConstant().annotatedWith(new PropertyImpl(configurablePropertyClass)).to(value);
  }

  protected void autoInstallModules() {
    GuiceInjectionUtil.getModules().stream()
        .filter(m -> GuiceInjectionUtil.isConcrete(m))
        .forEach(m -> installModule(m));
  }

  protected void installModule(final Class<? extends GuiceApplicationModule> moduleClass) {
    try {
      install(moduleClass.getDeclaredConstructor().newInstance());
    } catch (Exception e) {
      throw new Error("Unable to install module: " + moduleClass, e);
    }
  }

  protected void autoBindImplementations() {
    GuiceInjectionUtil.getInjectionableClasses().forEach(c -> doBind(c));
    GuiceInjectionUtil.getProviders().forEach(c -> doBind(c));
  }

  protected void doBind(final Class c) {
    if (GuiceInjectionUtil.implementsInterface(c)) {
      Arrays.stream(c.getInterfaces()).forEach(i -> doBindInterface(c, i));
    } else {
      bind(c);
    }
  }

  protected void doBindInterface(final Class c, final Class iface) {
    if (Provider.class.equals(iface)) {
      final AnnotatedBindingBuilder annotatedBindingBuilder =
          bind(GuiceInjectionUtil.getProviderType(c));
      GuiceInjectionUtil.bindQualifiers(annotatedBindingBuilder, c);
      annotatedBindingBuilder.toProvider(c);
    } else {
      final AnnotatedBindingBuilder annotatedBindingBuilder = bind(iface);
      GuiceInjectionUtil.bindQualifiers(annotatedBindingBuilder, c);
      annotatedBindingBuilder.to(c);
      setupMultiBinder(iface, c);

      mapGeneric(iface, c);
    }

    Arrays.stream(iface.getInterfaces()).forEach(iiface -> doBindInterface(c, iiface));
  }

  protected void setupMultiBinder(final Class iface, final Class c) {
    if (!hasMultipleBindings(iface)) {
      return;
    }

    Multibinder multibinder = multibinderMap.get(iface);
    if (multibinder == null) {
      multibinder = Multibinder.newSetBinder(binder(), iface);
      multibinderMap.put(iface, multibinder);
    }

    multibinder.addBinding().to(c);
  }

  public static boolean hasMultipleBindings(final Class iface) {
    return ApplicationHelper.getApplicationInstance().getReflections().getSubTypesOf(iface).size()
        > 1;
  }

  public TypeLiteral mapGeneric(final Class iface, final Class c) {
    final TypeLiteral typeLiteral = TypeLiteral.get(c);
    final TypeLiteral superTypeLiteral = typeLiteral.getSupertype(iface);

    bind(superTypeLiteral).to(c);
    LOGGER.info("binding {} to {}", superTypeLiteral, c);

    return superTypeLiteral;
  }
}
