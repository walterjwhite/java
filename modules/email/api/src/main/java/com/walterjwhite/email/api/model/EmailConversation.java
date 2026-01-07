package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import java.util.List;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@Builder
public class EmailConversation extends AbstractEntity {

  @Column protected String uuid;

  @EqualsAndHashCode.Exclude protected List<Email> emails;
}
