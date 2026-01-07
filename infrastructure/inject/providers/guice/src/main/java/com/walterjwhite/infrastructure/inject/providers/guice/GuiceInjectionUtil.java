package com.walterjwhite.infrastructure.inject.providers.guice;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.walterjwhite.infrastructure.inject.core.NoInject;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Qualifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuiceInjectionUtil {
  public static Set<Class<? extends GuiceApplicationModule>> getModules() {
    return ApplicationHelper.getApplicationInstance()
        .getReflections()
        .getSubTypesOf(GuiceApplicationModule.class);
  }

  public static Stream<Class> getInjectionableClasses() {
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

  public static Stream<Class<? extends Provider>> getProviders() {
    return ApplicationHelper.getApplicationInstance()
        .getReflections()
        .getSubTypesOf(Provider.class)
        .stream()
        .filter(injectionableClass -> isConcrete(injectionableClass));
  }

  public static boolean implementsInterface(final Class c) {
    return c.getInterfaces() != null && c.getInterfaces().length > 0;
  }

  public static boolean isConcrete(final Class c) {
    return c != null && !Modifier.isAbstract(c.getModifiers());
  }

  public static Class getProviderType(final Class c) {
    return (Class) ((ParameterizedType) c.getGenericInterfaces()[0]).getActualTypeArguments()[0];
  }

  public static void bindQualifiers(
      final AnnotatedBindingBuilder annotatedBindingBuilder, final Class c) {
    getQualifiers(c).forEach(qualifier -> annotatedBindingBuilder.annotatedWith(qualifier));
  }

  public static Set<Annotation> getQualifiers(final Class c) {
    final Set<Annotation> qualifiers = new HashSet<>();
    for (final Annotation annotation : c.getAnnotations()) {
      LOGGER.debug("checking annotation: {}", annotation);
      if (annotation.annotationType().isAnnotationPresent(Qualifier.class)) {
        qualifiers.add(annotation);
      }
    }

    return qualifiers;
  }
}
