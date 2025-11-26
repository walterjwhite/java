package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class EmailAccount extends AbstractEntity {
  @EqualsAndHashCode.Exclude protected String name;

  protected String emailAddress;

  public EmailAccount withEmailAddress(final String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  public EmailAccount withName(final String name) {
    this.name = name;
    return this;
  }
}
