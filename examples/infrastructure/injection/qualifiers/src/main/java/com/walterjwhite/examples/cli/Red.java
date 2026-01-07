package com.walterjwhite.examples.cli;

import jakarta.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Red {}
