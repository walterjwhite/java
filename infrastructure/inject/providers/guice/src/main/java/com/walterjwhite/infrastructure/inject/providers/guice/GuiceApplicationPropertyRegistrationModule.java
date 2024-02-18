package com.walterjwhite.infrastructure.inject.providers.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.multibindings.OptionalBinder;
import com.google.inject.util.Providers;
import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import com.walterjwhite.infrastructure.inject.core.NoInject;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.PropertyHelper;
import com.walterjwhite.property.impl.PropertyImpl;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;


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






    bind(Reflections.class).toInstance(ApplicationHelper.getApplicationInstance().getReflections());
    bind(ApplicationInstance.class).toInstance(ApplicationHelper.getApplicationInstance());
    bind(PropertyManager.class)
        .toInstance(ApplicationHelper.getApplicationInstance().getPropertyManager());


    bind(SecretService.class)
        .toInstance(ApplicationHelper.getApplicationInstance().getSecretService());
  }

  protected void bindProperties() {
    propertyManager.getKeys().forEach(configurableProperty -> bindToProperty(configurableProperty));
  }

  public void bindToProperty(Class<? extends ConfigurableProperty> configurablePropertyClass) {
    final String value = propertyManager.get(configurablePropertyClass);

    if(value == null) {




      return;
    }

      bindConstant().annotatedWith(new PropertyImpl(configurablePropertyClass)).to(value);
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
    getInjectionableClasses().forEach(c -> doBind(c));
    getProviders().forEach(c -> doBind(c));
  }

  private static Stream<Class> getInjectionableClasses() {
    return ApplicationHelper.getApplicationInstance()
        .getReflections()
        .getConstructorsAnnotatedWith(Inject.class)
        .stream()
        .filter(
            injectionableClass ->
                !NoInject.class.isAssignableFrom(injectionableClass.getDeclaringClass())
                    && isConcrete(injectionableClass.getDeclaringClass()))
        .map(c -> c.getDeclaringClass());
  }

  private static Stream<Class<? extends Provider>> getProviders() {
    return ApplicationHelper.getApplicationInstance()
        .getReflections()
        .getSubTypesOf(Provider.class)
        .stream()
        .filter(injectionableClass -> isConcrete(injectionableClass));
  }

  private static boolean implementsInterface(final Class c) {
    return c.getInterfaces() != null && c.getInterfaces().length > 0;
  }

  private static boolean isConcrete(final Class c) {
    return c != null && !Modifier.isAbstract(c.getModifiers());
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



    Arrays.stream(iface.getInterfaces()).forEach(iiface -> doBindInterface(c, iiface));
  }

  private static Class getProviderType(final Class c) {
    return (Class) ((ParameterizedType) c.getGenericInterfaces()[0]).getActualTypeArguments()[0];
  }
}
