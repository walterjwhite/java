package com.walterjwhite.examples.spring.request;

import com.walterjwhite.web.servlet.request.RequestFilter;
import jakarta.servlet.DispatcherType;
import java.util.EnumSet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class RequestFilterConfiguration {

  @Bean
  public FilterRegistrationBean<RequestFilter> requestFilterRegistration() {
    FilterRegistrationBean<RequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new RequestFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setName("requestFilter");
    filterRegistrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST));
    filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 50);
    return filterRegistrationBean;
  }
}
