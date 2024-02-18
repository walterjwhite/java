package com.walterjwhite.email.modules.template.model;


import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.email.api.model.EmailAccount;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class EmailTemplateContact extends AbstractEntity {
  protected EmailAccount emailAccount;

  protected EmailTemplate emailTemplate;

  protected EmailRecipientType emailRecipientType;
}
