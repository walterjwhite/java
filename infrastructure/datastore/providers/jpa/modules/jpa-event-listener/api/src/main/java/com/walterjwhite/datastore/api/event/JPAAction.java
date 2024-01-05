package com.walterjwhite.datastore.api.event;

import com.walterjwhite.datastore.api.event.enumeration.JPAActionType;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor
@PersistenceCapable
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class JPAAction extends AbstractEntity {

  protected Class entityClass;

  protected Integer entityId;

  protected JPAActionType action;

  @EqualsAndHashCode.Exclude protected LocalDateTime dateTime;
}
