package com.walterjwhite.examples.cli;

import jakarta.inject.Qualifier;
import java.lang.annotation.Annotation;
import org.junit.jupiter.api.Test;

public class RedProviderTest {
  @Test
  void test_annotations() {

    for (final Annotation annotation : RedProvider.class.getAnnotations()) {
      System.out.println("annotation class: " + annotation.annotationType());

      if (annotation.annotationType().isAnnotationPresent(Qualifier.class)) {
        System.out.println("annotation is qualifier annotation present");
      }
      System.out.println("annotation: " + annotation + annotation.annotationType());
    }
  }
}
