package com.walterjwhite.email.organization.plugins.count;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.organization.api.configuration.rule.EmailMatcherRule;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.time.LocalDate;

public enum CountKeyType implements ConfigurableProperty {
  @DefaultValue
  Date() {
    public String getKey(
        final String folderName,
        final PrivateEmailAccount privateEmailAccount,
        final EmailMatcherRule emailMatcherRule,
        final Email email) {
      return LocalDate.now().toString();
    }
  };

  public abstract String getKey(
      final String folderName,
      final PrivateEmailAccount privateEmailAccount,
      final EmailMatcherRule emailMatcherRule,
      final Email email);
}
