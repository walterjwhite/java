package com.walterjwhite.google.guice.executor.provider.model.archived;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// implementation in Java code)
// mark the default provider in the enum to eliminate a database call, the enum will store a
// ServiceProvider
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class ServiceProviderType extends AbstractNamedEntity {
  protected String serviceInterfaceClassname;
  protected ServiceProvider defaultServiceProvider;

  protected List<ServiceProvider> serviceProviders = new ArrayList<>();

  public ServiceProviderType(
      String name,
      String description,
      String serviceInterfaceClassname,
      ServiceProvider defaultServiceProvider) {
    super(name, description);
    this.serviceInterfaceClassname = serviceInterfaceClassname;
    this.defaultServiceProvider = defaultServiceProvider;
  }
}
