package com.walterjwhite.template.providers.stringtemplate.service;

import com.walterjwhite.inject.cli.providers.guice.test.GuiceTestModule;
import com.walterjwhite.inject.cli.providers.guice.test.PropertyValuePair;
import com.walterjwhite.template.providers.stringtemplate.StringTemplateModule;
import org.reflections.Reflections;

public class StringTemplateTestModule extends GuiceTestModule {
  public StringTemplateTestModule(Class testClass, PropertyValuePair... propertyValuePairs) {
    super(testClass, propertyValuePairs);
  }

  public StringTemplateTestModule(
      Class testClass, Reflections reflections, PropertyValuePair... propertyValuePairs) {
    super(testClass, reflections, propertyValuePairs);
  }

  @Override
  protected void configure() {
    super.configure();

    install(new StringTemplateModule());
  }
}
