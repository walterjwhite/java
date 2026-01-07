package com.walterjwhite.examples.spring.rest;

import com.walterjwhite.infrastructure.inject.providers.spring.SpringApplicationModule;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class RestControllerTestSpringApplicationModule implements SpringApplicationModule {
  @Override
  public String[] scanBasePackages() {
    return new String[] {"com.walterjwhite"};
  }

  @Override
  public boolean isWebServer() {
    return true;
  }
}
