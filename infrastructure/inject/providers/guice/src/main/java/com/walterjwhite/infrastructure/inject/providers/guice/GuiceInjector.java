package com.walterjwhite.infrastructure.inject.providers.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import jakarta.enterprise.util.AnnotationLiteral;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GuiceInjector implements com.walterjwhite.infrastructure.inject.core.Injector {
  protected transient volatile GuiceApplicationPropertyRegistrationModule
      guiceApplicationPropertyRegistrationModule;
  protected transient volatile Injector injector;

  public void initialize()
      throws InstantiationException, IllegalAccessException, NoSuchMethodException,
          InvocationTargetException {
    createInjector();
  }

  public void createInjector()
      throws InstantiationException, IllegalAccessException, NoSuchMethodException,
          InvocationTargetException {
    guiceApplicationPropertyRegistrationModule =
        new GuiceApplicationPropertyRegistrationModule(getGuiceApplicationModules());
    injector = Guice.createInjector(getStage(), guiceApplicationPropertyRegistrationModule);
  }

  protected Stage getStage() {
    return GuiceApplicationEnvironmentMapping.getFromApplicationEnvironment(
            ApplicationHelper.getApplicationInstance()
                .getApplicationSession()
                .getApplicationIdentifier()
                .getApplicationEnvironment())
        .getStage();
  }

  protected GuiceApplicationModule[] getGuiceApplicationModules()
      throws IllegalAccessException, InstantiationException, NoSuchMethodException,
          InvocationTargetException {
    final Set<Class<? extends GuiceApplicationModule>> guiceApplicationModuleSet =
        ApplicationHelper.getApplicationInstance()
            .getReflections()
            .getSubTypesOf(GuiceApplicationModule.class);

    if (guiceApplicationModuleSet != null && guiceApplicationModuleSet.size() > 0) {
      final GuiceApplicationModule[] guiceApplicationModules =
          new GuiceApplicationModule[guiceApplicationModuleSet.size()];
      int i = 0;
      for (Class<? extends GuiceApplicationModule> guiceApplicationModuleClass :
          guiceApplicationModuleSet) {
        guiceApplicationModules[i++] =
            guiceApplicationModuleClass.getDeclaredConstructor().newInstance();
      }

      return guiceApplicationModules;
    }

    return null;
  }

  @Override
  public <T> T getInstance(Class<T> instanceClass, final AnnotationLiteral... annotationLiterals) {
    return injector.getInstance(instanceClass);
  }
}
