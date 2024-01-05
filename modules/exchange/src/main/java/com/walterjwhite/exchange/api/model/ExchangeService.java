package com.walterjwhite.exchange.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class ExchangeService extends AbstractNamedEntity {

  protected String exchangeVersion;

  protected String uri;
}
