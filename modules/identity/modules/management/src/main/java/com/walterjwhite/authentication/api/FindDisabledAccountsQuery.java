package com.walterjwhite.authentication.api;

import com.walterjwhite.datastore.query.AbstractQuery;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import java.util.List;

public class FindDisabledAccountsQuery extends AbstractQuery<ClientAccount, List> {
  public FindDisabledAccountsQuery() {
    super(0, -1, ClientAccount.class, List.class, false);
  }
}
