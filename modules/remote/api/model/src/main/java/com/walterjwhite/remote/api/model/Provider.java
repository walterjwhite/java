package com.walterjwhite.remote.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.remote.api.enumeration.ProviderType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
public class Provider extends AbstractNamedEntity {

  protected ProviderType providerType;

  public Provider(String moduleClassName, ProviderType providerType) {
    super(moduleClassName);
    this.providerType = providerType;
  }
}
