package com.walterjwhite.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a value as sensitive and must be encrypted. TODO: specify encryption algorithm, key size
 * etc. TODO: leverage encryption project (annotation, enum, java code for encrypting, decrypting,
 * and digesting)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.METHOD})
public @interface Sensitive {}
