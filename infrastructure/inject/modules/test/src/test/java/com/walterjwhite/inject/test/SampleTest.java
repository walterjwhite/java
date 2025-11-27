package com.walterjwhite.inject.test;

import com.walterjwhite.inject.test.annotation.UseTestPropertyProvider;
import jakarta.inject.Inject;

@UseTestPropertyProvider(SampleTestConfiguration.class)
public class SampleTest {
  @Inject protected SampleService sampleService;

}
