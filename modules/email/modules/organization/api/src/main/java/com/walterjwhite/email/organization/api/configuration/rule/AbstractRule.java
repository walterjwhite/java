package com.walterjwhite.email.organization.api.configuration.rule;

import com.walterjwhite.email.api.model.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;







@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractRule {

  @EqualsAndHashCode.Exclude protected boolean invert;

  public abstract boolean matches(final Email email);
}
