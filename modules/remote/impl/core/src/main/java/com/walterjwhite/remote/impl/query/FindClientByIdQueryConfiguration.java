package com.walterjwhite.remote.impl.query;

import com.walterjwhite.datastore.query.AbstractSingularQuery;
import com.walterjwhite.remote.api.model.Client;
import lombok.Getter;

@Getter
public class FindClientByIdQueryConfiguration extends AbstractSingularQuery<Client> {
  protected final String entityId;

  public FindClientByIdQueryConfiguration(String entityId) {
    super(Client.class, false);
    this.entityId = entityId;
  }
}
