package com.walterjwhite.datastore.api.event;

import com.walterjwhite.datastore.api.event.annotation.OnJPAAction;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.Repository;
import java.time.LocalDateTime;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

// @Decorator
@Interceptor
public class AuditingRepositoryInterceptor /*implements MethodInterceptor*/ {
  @AroundInvoke
  public Object onJPAAction(InvocationContext invocationContext) throws Exception {
    final OnJPAAction onJPAAction = invocationContext.getMethod().getAnnotation(OnJPAAction.class);

    //        if(JPAExecutionTimeType.Pre.equals(onJPAAction.executionTime())) {
    JPAAction jpaAction =
        new JPAAction(
            invocationContext.getParameters()[0].getClass(),
            ((AbstractEntity) invocationContext.getParameters()[0]).getId(),
            onJPAAction.action(),
            LocalDateTime.now());
    //        }

    ((Repository) invocationContext.getTarget()).getEntityManager().persist(jpaAction);

    return invocationContext.proceed();
  }
}
