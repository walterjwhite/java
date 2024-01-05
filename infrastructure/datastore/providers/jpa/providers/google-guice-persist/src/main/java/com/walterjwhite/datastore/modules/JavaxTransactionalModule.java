package com.walterjwhite.datastore.modules;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import javax.transaction.Transactional;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class JavaxTransactionalModule extends AbstractModule {
  @Override
  protected void configure() {
    TransactionalInterceptor transactionInterceptor = new TransactionalInterceptor();

    requestInjection(transactionInterceptor);
    // class-level @Transacational
    bindInterceptor(
        Matchers.annotatedWith(Transactional.class), Matchers.any(), transactionInterceptor);
    // method-level @Transacational
    bindInterceptor(
        Matchers.any(), Matchers.annotatedWith(Transactional.class), transactionInterceptor);
  }

  private class TransactionalInterceptor implements MethodInterceptor {
    @javax.transaction.Transactional
    public Object invoke(MethodInvocation invocation) throws Throwable {
      return invocation.proceed();
    }
  }
}
