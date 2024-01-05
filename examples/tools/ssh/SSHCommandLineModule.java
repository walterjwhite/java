package com.walterjwhite.examples.ssh;

import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.api.repository.CriteriaBuilderModule;
import com.walterjwhite.google.guice.cli.AbstractCommandLineModule;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.scm.providers.jgit.SSHModule;
import org.reflections.Reflections;

public class SSHCommandLineModule extends AbstractCommandLineModule {
  public SSHCommandLineModule(PropertyManager propertyManager, Reflections reflections) {
    super(propertyManager, reflections, SSHOperatingMode.class);
  }

  @Override
  protected void doCliConfigure() {
    install(new SSHModule());

    install(new CriteriaBuilderModule());
    install(new GoogleGuicePersistModule(/*propertyManager, reflections*/ ));
  }
}
