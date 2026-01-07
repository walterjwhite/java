package com.walterjwhite.examples.spring.mvc;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.walterjwhite.examples.spring")
@EnableJpaRepositories(basePackages = "com.walterjwhite")
public class PersonConfig {}
