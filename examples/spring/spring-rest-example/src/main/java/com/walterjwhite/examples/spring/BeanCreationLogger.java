package com.walterjwhite.examples.spring;

import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class BeanCreationLogger implements InstantiationAwareBeanPostProcessor, BeanPostProcessor {
    private final AtomicLong seq = new AtomicLong();

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        long id = seq.incrementAndGet();
        LOGGER.info("[BEAN-{}] BeforeInstantiation: name='{}' class={}", id, beanName, beanClass.getName());
        return null; // returning non-null would short-circuit instantiation (don't do that unless you mean to)
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        long id = seq.incrementAndGet();
        LOGGER.info("[BEAN-{}] AfterInstantiation: name='{}' class={}", id, beanName, bean.getClass().getName());
        return true; // return true to allow property population
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        long id = seq.incrementAndGet();
        LOGGER.info("[BEAN-{}] BeforeInitialization: name='{}' class={}", id, beanName, bean.getClass().getName());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        long id = seq.incrementAndGet();
        LOGGER.info("[BEAN-{}] AfterInitialization: name='{}' class={}", id, beanName, bean.getClass().getName());
        return bean;
    }
}
