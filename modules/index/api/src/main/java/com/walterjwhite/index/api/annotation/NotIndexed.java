package com.walterjwhite.index.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Unless otherwise specified, all entities inheriting from AbstractEntity will be automatically
 * indexed TODO: 1. problem: need to mark jobs as not indexed, that means jobs will be dependent
 * upon elasticsearch api
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotIndexed {}
