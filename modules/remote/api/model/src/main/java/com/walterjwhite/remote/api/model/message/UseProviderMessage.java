package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.model.Provider;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable

public class UseProviderMessage extends Message {
  protected Provider provider;
}
