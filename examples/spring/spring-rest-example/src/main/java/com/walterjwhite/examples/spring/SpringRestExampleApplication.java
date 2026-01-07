package com.walterjwhite.examples.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SpringRestExampleApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringRestExampleApplication.class, args);
  }

}
