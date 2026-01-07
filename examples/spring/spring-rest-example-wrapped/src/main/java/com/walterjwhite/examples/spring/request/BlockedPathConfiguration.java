package com.walterjwhite.examples.spring.request;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;

@Configuration
@Profile("prod")
@ConditionalOnProperty(prefix = "app.blocked-paths", name = "enabled", havingValue = "true")
@Slf4j
public class BlockedPathConfiguration {
  private static final List<String> SAFE_EXCLUDES =
