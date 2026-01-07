package com.walterjwhite.infrastructure.inject.providers.spring;

import com.walterjwhite.infrastructure.inject.core.Injector;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import jakarta.enterprise.util.AnnotationLiteral;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Getter
@RequiredArgsConstructor
public class SpringInjector implements Injector {
  protected transient volatile SpringApplicationPropertyRegistrationModule
      springApplicationPropertyRegistrationModule;
  protected transient volatile AnnotationConfigApplicationContext applicationContext;
  protected transient volatile BeanFactory beanFactory;

  public void initialize()
      throws InstantiationException,
          IllegalAccessException,
          NoSuchMethodException,
          InvocationTargetException {
    final SpringApplicationModule springApplicationModule = getSpringApplicationModule();

    applicationContext =
        new AnnotationConfigApplicationContext(springApplicationModule.scanBasePackages());



    beanFactory = applicationContext.getAutowireCapableBeanFactory();
  }

  protected SpringApplicationModule getSpringApplicationModule()
      throws IllegalAccessException,
          InstantiationException,
          NoSuchMethodException,
          InvocationTargetException {
    final Iterator<Class<? extends SpringApplicationModule>> springApplicationModuleIterator =
        ApplicationHelper.getApplicationInstance()
            .getReflections()
            .getSubTypesOf(SpringApplicationModule.class)
            .iterator();

    if (springApplicationModuleIterator.hasNext()) {
      return springApplicationModuleIterator.next().getDeclaredConstructor().newInstance();
    }

    return null;
  }

  @Override
  public <T> T getInstance(Class<T> instanceClass, final AnnotationLiteral... annotationLiterals) {
    return beanFactory.getBean(instanceClass);
  }
}
