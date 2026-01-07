package com.walterjwhite.inject.test.runner;

import com.walterjwhite.infrastructure.inject.core.ApplicationInstance;
import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.infrastructure.inject.core.service.ServiceManager;
import com.walterjwhite.inject.test.annotation.UseTestPropertyProvider;
import com.walterjwhite.inject.test.property.PropertyValuePair;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.SecretService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.Getter;
import org.junit.jupiter.api.extension.*;
import org.reflections.Reflections;

@Getter
public class TestApplicationRunner extends ApplicationInstance
    implements Extension,
        BeforeAllCallback,
        AfterAllCallback,
        TestInstancePostProcessor,
        BeforeEachCallback,
        AfterEachCallback,
        ParameterResolver {
  protected final PropertyValuePair[] propertyValuePairs;
  protected final Class testClass;

  public TestApplicationRunner(
      Reflections reflections,
      PropertyManager propertyManager,
      ServiceManager serviceManager,
      SecretService secretService,
      Injector injector,
      PropertyValuePair[] propertyValuePairs,
      Class testClass) {
    super(reflections, propertyManager, serviceManager, secretService, injector);
    this.propertyValuePairs = propertyValuePairs;
    this.testClass = testClass;
  }


  protected void doRun() throws Exception {
  }

  protected PropertyValuePair[] getTestPropertyProvider()
      throws InstantiationException,
          IllegalAccessException,
          NoSuchMethodException,
          InvocationTargetException {
    return ((UseTestPropertyProvider) testClass.getAnnotation(UseTestPropertyProvider.class))
        .value()
        .getDeclaredConstructor()
        .newInstance()
        .getTestProperties();
  }


  @Override
  public void afterAll(ExtensionContext context) {}

  @Override
  public void afterEach(ExtensionContext context) {
    Object testInstance = context.getRequiredTestInstance();
    Method testMethod = context.getRequiredTestMethod();
    Throwable testException = context.getExecutionException().orElse(null);

  }

  @Override
  public void beforeAll(ExtensionContext context) {}

  @Override
  public void beforeEach(ExtensionContext context) {
    Object testInstance = context.getRequiredTestInstance();
    Method testMethod = context.getRequiredTestMethod();

  }

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return false;
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return null;
  }

  @Override
  public void postProcessTestInstance(Object testInstance, ExtensionContext context) {}
}
