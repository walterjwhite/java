package com.walterjwhite.email.organization.plugins.count;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.organization.api.Action;
import com.walterjwhite.email.organization.api.configuration.rule.EmailMatcherRule;
import com.walterjwhite.property.api.annotation.Property;
import java.util.HashMap;
import java.util.Map;
import jakarta.inject.Inject;

// @NoArgsConstructor(onConstructor_ = @Inject)
public class CountAction implements Action {
  protected final Map<Object, Integer> countMap = new HashMap<>();

  protected final CountKeyType countKeyType;

  @Inject
  public CountAction(@Property(CountKeyType.class) CountKeyType countKeyType) {
    this.countKeyType = countKeyType;
  }

  @Override
  public void execute(
      final String folderName,
      final PrivateEmailAccount privateEmailAccount,
      EmailMatcherRule emailMatcherRule,
      final Email email) {
    final Object key =
        countKeyType.getKey(folderName, privateEmailAccount, emailMatcherRule, email);
    update(key, getCount(key));
  }

  protected int getCount(final Object key) {
    if (countMap.containsKey(key)) return countMap.get(key);

    return 0;
  }

  protected void update(final Object key, final int count) {
    countMap.put(key, count + 1);
  }
}
