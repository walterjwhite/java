package com.walterjwhite.google.guice.executor.provider;

import com.walterjwhite.property.api.SecretService;
import java.util.concurrent.atomic.AtomicInteger;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.reflections.Reflections;

public class ServiceSelectorProvider implements Provider<SecretService> {
  protected final AtomicInteger count = new AtomicInteger();
  // cannot use reflections here because we cannot order the results
  protected final Reflections reflections;

  @Inject
  public ServiceSelectorProvider(Reflections reflections) {
    this.reflections = reflections;
  }
  @Override
  public SecretService get() {
    final int currentCount = count.getAndIncrement();
    //       reflections.getSubTypesOf(SecretService.class).
    return null;
  }
  // 1 try getting token from CLI, if that fails, try getting via email
  //   final Provider<TokenService> tokenServiceProvider = null;
  //   public void
  //   try{
  //       tokenServiceProvider.get().getToken();
  //   } catch(Exception e){
  //       if(tokenServiceProvider.hasMoreProviders()){
  //       }
  //   }
}
