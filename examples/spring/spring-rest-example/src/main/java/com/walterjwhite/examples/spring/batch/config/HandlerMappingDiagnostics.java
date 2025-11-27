package com.walterjwhite.examples.spring.batch.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

@Component
public class HandlerMappingDiagnostics implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableListableBeanFactory bf = event.getApplicationContext().getBeanFactory();
        System.out.println("----- HandlerMappingDiagnostics: scanning bean definitions -----");
        for (String name : bf.getBeanDefinitionNames()) {
            try {
                Class<?> type = bf.getType(name); // does not force full instantiation
                if (type != null && HandlerMapping.class.isAssignableFrom(type)) {
                    System.out.printf("Candidate HandlerMapping bean: name=%s, type=%s%n", name, type.getName());
                }
                var def = bf.getBeanDefinition(name);
                if (def.getFactoryMethodName() != null || def.getBeanClassName() != null) {
                    System.out.printf("  beanDef: name=%s, beanClassName=%s, factoryMethod=%s%n",
                            name, def.getBeanClassName(), def.getFactoryMethodName());
                }
            } catch (Exception ex) {
                System.out.printf("  trouble getting type for bean '%s': %s%n", name, ex);
            }
        }
        System.out.println("----- end diagnostics -----");
    }
}