package com.walterjwhite.data.pipe.modules.vaadin;

import com.walterjwhite.data.pipe.impl.DataPipeModule;
import com.walterjwhite.data.pipe.modules.csv.CSVDataPipeModule;
import com.walterjwhite.inject.cli.providers.guice.test.GuiceTestModule;
import com.walterjwhite.inject.cli.providers.guice.test.PropertyValuePair;
import com.walterjwhite.vaadin.data.pipe.VaadinModule;
import org.reflections.Reflections;

public class VaadinTestModule extends GuiceTestModule {
  public VaadinTestModule(Class testClass, PropertyValuePair... propertyValuePairs) {
    super(testClass, propertyValuePairs);
  }

  public VaadinTestModule(
      Class testClass, Reflections reflections, PropertyValuePair... propertyValuePairs) {
    super(testClass, reflections, propertyValuePairs);
  }

  @Override
  protected void configure() {
    super.configure();

    install(new DataPipeModule());
    install(new CSVDataPipeModule());
    install(new VaadinModule());
  }
}
