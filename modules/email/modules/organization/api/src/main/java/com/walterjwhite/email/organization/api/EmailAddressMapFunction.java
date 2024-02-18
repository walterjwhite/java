package com.walterjwhite.email.organization.api;

import com.walterjwhite.email.api.model.EmailEmailAccount;
import java.util.function.Function;


public class EmailAddressMapFunction implements Function<EmailEmailAccount, String> {
  @Override
  public String apply(EmailEmailAccount o) {
    return o.getEmailAccount().getEmailAddress();
  }
}
