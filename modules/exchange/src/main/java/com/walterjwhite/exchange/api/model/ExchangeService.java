package com.walterjwhite.exchange.api.model;


import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class ExchangeService {

  protected String exchangeVersion;

  protected String uri;
}
