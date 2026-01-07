package com.walterjwhite.examples.spring;

import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class BeanCreationLogger
    implements InstantiationAwareBeanPostProcessor, BeanPostProcessor, ApplicationContextAware {
  private final AtomicLong seq = new AtomicLong();

  private transient ApplicationContext applicationContext;


  @Override
  public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
      throws BeansException {
    long id = seq.incrementAndGet();
    LOGGER.debug(
        "[BEAN-{}] BeforeInstantiation: name='{}' class={}", id, beanName, beanClass.getName());
    return null; // returning non-null would short-circuit instantiation (don't do that unless you
  }

  @Override
  public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
    long id = seq.incrementAndGet();
    LOGGER.debug(
        "[BEAN-{}] AfterInstantiation: name='{}' class={}",
        id,
        beanName,
        bean.getClass().getName());
    return true; // return true to allow property population
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    long id = seq.incrementAndGet();
    LOGGER.debug(
        "[BEAN-{}] BeforeInitialization: name='{}' class={}",
        id,
        beanName,
        bean.getClass().getName());
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    long id = seq.incrementAndGet();
    LOGGER.debug(
        "[BEAN-{}] AfterInitialization: name='{}' class={}",
        id,
        beanName,
        bean.getClass().getName());
    return bean;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
