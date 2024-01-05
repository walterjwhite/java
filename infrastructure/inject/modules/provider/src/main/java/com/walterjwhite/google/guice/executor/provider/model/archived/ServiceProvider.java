package com.walterjwhite.google.guice.executor.provider.model.archived;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class ServiceProvider extends AbstractNamedEntity {
  protected String serviceClassname;
  protected ServiceProviderType serviceProviderType;
  //
  //    protected boolean defaultProvider;

  public ServiceProvider(
      String name,
      String description,
      String serviceClassname,
      ServiceProviderType serviceProviderType) {
    super(name, description);
    this.serviceClassname = serviceClassname;
    this.serviceProviderType = serviceProviderType;
  }
}
