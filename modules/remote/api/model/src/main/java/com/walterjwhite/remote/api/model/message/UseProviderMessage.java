package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.model.Provider;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class UseProviderMessage extends Message {
  protected Provider provider;
}
