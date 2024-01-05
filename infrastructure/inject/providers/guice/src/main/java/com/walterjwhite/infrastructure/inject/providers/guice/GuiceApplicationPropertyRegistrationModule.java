package com.walterjwhite.infrastructure.inject.providers.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import com.walterjwhite.infrastructure.inject.core.NoInject;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
// import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.PropertyHelper;
import com.walterjwhite.property.impl.PropertyImpl;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;

/** This registers all properties as Guice "beans" */
@RequiredArgsConstructor
public class GuiceApplicationPropertyRegistrationModule extends AbstractModule {
  protected final GuiceApplicationModule[] guiceApplicationModules;
  protected transient PropertyManager propertyManager;

  protected void configure() {
    propertyManager = ApplicationHelper.getApplicationInstance().getPropertyManager();

    bindCommon();
    bindProperties();

    autoInstallModules();
    autoBindImplementations();
  }

  protected void bindCommon() {
    // this is automatically handled later by autoInstallModules
    //    if (guiceApplicationModules != null) {
    //      Arrays.stream(guiceApplicationModules)
    //          .forEach(guiceApplicationModule -> install(guiceApplicationModule));
    //    }

    bind(Reflections.class).toInstance(ApplicationHelper.getApplicationInstance().getReflections());
    bind(ApplicationInstance.class).toInstance(ApplicationHelper.getApplicationInstance());
    bind(PropertyManager.class)
        .toInstance(ApplicationHelper.getApplicationInstance().getPropertyManager());
    //    bind(ServiceManager.class)
    //        .toInstance(ApplicationHelper.getApplicationInstance().getServiceManager());
    bind(SecretService.class)
        .toInstance(ApplicationHelper.getApplicationInstance().getSecretService());
  }

  protected void bindProperties() {
    propertyManager.getKeys().forEach(configurableProperty -> bindToProperty(configurableProperty));
  }

  public void bindToProperty(Class<? extends ConfigurableProperty> configurablePropertyClass) {
    final String value = propertyManager.get(configurablePropertyClass);

    if (PropertyHelper.isOptional(configurablePropertyClass)) {
      // OptionalBinder.newOptionalBinder(binder(),
      // configurablePropertyClass).setDefault().toInstance(value));
      // OptionalBinder.newOptionalBinder(binder().)
      bindConstant()
          .annotatedWith(new PropertyImpl(configurablePropertyClass))
          .to(Optional.ofNullable(value));
    } else {

      if (value != null)
        bindConstant().annotatedWith(new PropertyImpl(configurablePropertyClass)).to(value);
    }
  }

  protected void autoInstallModules() {
    getModules().stream().filter(m -> isConcrete(m)).forEach(m -> installModule(m));
  }

  protected void installModule(final Class<? extends GuiceApplicationModule> moduleClass) {
    try {
      install(moduleClass.getDeclaredConstructor().newInstance());
    } catch (Exception e) {
      throw new Error("Unable to install module: " + moduleClass, e);
    }
  }

  private static Set<Class<? extends GuiceApplicationModule>> getModules() {
    return ApplicationHelper.getApplicationInstance()
        .getReflections()
        .getSubTypesOf(GuiceApplicationModule.class);
  }

  protected void autoBindImplementations() {
    getInjectionableClasses().stream().filter(c -> isConcrete(c)).forEach(c -> doBind(c));
  }

  private static List<Class> getInjectionableClasses() {
    return ApplicationHelper.getApplicationInstance()
        .getReflections()
        .getConstructorsAnnotatedWith(Inject.class)
        .stream()
        .filter(
            injectionableClass ->
                !NoInject.class.isAssignableFrom(injectionableClass.getDeclaringClass()))
        .map(
            c ->
                c
                    .getDeclaringClass()) /*.filter(c -> !c.isAssignableFrom(AbstractCommandLineHandler.class))*/
        .collect(Collectors.toList());
  }

  private static boolean implementsInterface(final Class c) {
    return c.getInterfaces() != null && c.getInterfaces().length > 0;
  }

  private static boolean isConcrete(final Class c) {
    return !Modifier.isAbstract(c.getModifiers());
  }

  protected void doBind(final Class c) {
    if (implementsInterface(c)) {
      Arrays.stream(c.getInterfaces()).forEach(i -> doBindInterface(c, i));
    } else {
      bind(c);
    }
  }

  protected void doBindInterface(final Class c, final Class iface) {
    if (Provider.class.equals(iface)) {
      bind(getProviderType(c)).toProvider(c);
    } else {
      bind(iface).to(c);
    }

    // bind all parent interfaces as well
    // CAUTION ...
    Arrays.stream(iface.getInterfaces()).forEach(iiface -> doBindInterface(c, iiface));
  }

  private static Class getProviderType(final Class c) {
    return (Class) ((ParameterizedType) c.getGenericInterfaces()[0]).getActualTypeArguments()[0];
  }
}
