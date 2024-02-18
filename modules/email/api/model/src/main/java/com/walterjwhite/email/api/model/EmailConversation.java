package com.walterjwhite.email.api.model;


import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import java.util.List;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@Builder
public class EmailConversation extends AbstractEntity {


  @Column
  protected String uuid;

  @EqualsAndHashCode.Exclude protected List<Email> emails;
}
