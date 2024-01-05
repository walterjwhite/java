package com.walterjwhite.template.providers.stringtemplate.service;

import com.google.inject.Injector;
import com.walterjwhite.google.guice.GuiceHelper;
import com.walterjwhite.template.api.model.Template;
import com.walterjwhite.template.api.service.TemplateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringTemplateTemplateServiceTest {
  protected Injector injector;
  protected TemplateService templateService;

  @Before
  public void onBefore() {
    GuiceHelper.addModules(new StringTemplateTestModule(getClass()));
    GuiceHelper.setup();
    injector = GuiceHelper.getGuiceApplicationInjector();
    templateService = injector.getInstance(TemplateService.class);
  }

  @Test
  public void basicTest() {
    final Template template = prepareTemplate();
    final ValueObject valueObject = prepareValueObject();

    final String rendered = render(template, valueObject);
    Assert.assertEquals("Hi Fred", rendered);
  }

  protected Template prepareTemplate() {
    Template template = new Template();
    template.setContents("Hi $ValueObject.name$");

    return template;
  }

  protected ValueObject prepareValueObject() {
    final ValueObject valueObject = new ValueObject();
    valueObject.setName("Fred");

    return valueObject;
  }

  protected String render(Template template, ValueObject valueObject) {
    return templateService.apply(template, valueObject);
  }
}
