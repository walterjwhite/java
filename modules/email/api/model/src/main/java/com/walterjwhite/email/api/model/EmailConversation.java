package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class EmailConversation extends AbstractEntity {

  protected String uuid;

  @EqualsAndHashCode.Exclude protected List<Email> emails;

  public EmailConversation(String uuid) {
    this();
    this.uuid = uuid;
  }

  public EmailConversation() {

    this.emails = new ArrayList<>();
  }
}
