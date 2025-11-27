package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class EmailEmailAccount extends AbstractEntity {

  protected EmailAccount emailAccount;

  protected Email email;

  protected EmailRecipientType emailRecipientType;
}
