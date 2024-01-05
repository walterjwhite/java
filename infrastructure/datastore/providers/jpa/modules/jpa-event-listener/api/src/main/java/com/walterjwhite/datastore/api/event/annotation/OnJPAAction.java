package com.walterjwhite.datastore.api.event.annotation;

import com.walterjwhite.datastore.api.event.enumeration.JPAActionType;
import java.lang.annotation.*;
import javax.interceptor.InterceptorBinding;

/** Registers a QueryBuilder for a given Query Configuration */
@InterceptorBinding
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnJPAAction {
  JPAActionType action();
  //  JPAExecutionTimeType executionTime();
}
