package com.walterjwhite.examples.proxy;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

@NoArgsConstructor(onConstructor_ = @Inject)
public class ByteBuddyProxyExampleCommandLineHandler implements CommandLineHandler {

  @Override
  public void run(String... arguments) {
    setupByteBuddy();
    testInvocation();
  }

  protected void setupByteBuddy() {
    ByteBuddyAgent.install();
    final ByteBuddy byteBuddy = new ByteBuddy();

    byteBuddy
        .redefine(ByteBuddyFoo.class)
        .method(ElementMatchers.named("anotherMethod").or(ElementMatchers.named("toString")))
        .intercept(MethodDelegation.to(MethodInterceptor.class))
        .make()
        .load(ByteBuddyFoo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
  }

  protected void testInvocation() {
    ByteBuddyFoo f = new ByteBuddyFoo();

    f.anotherMethod("message goes here");
    f.anotherMethod("message goes here");
    f.anotherMethod("message goes here");
  }
}
