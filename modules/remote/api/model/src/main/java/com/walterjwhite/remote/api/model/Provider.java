package com.walterjwhite.remote.api.model;

import com.walterjwhite.remote.api.enumeration.ProviderType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
@AllArgsConstructor
public class Provider {
  protected String moduleClassName;

  protected ProviderType providerType;
}
