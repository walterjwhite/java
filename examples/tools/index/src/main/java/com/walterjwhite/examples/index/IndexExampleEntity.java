package com.walterjwhite.examples.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class IndexExampleEntity extends AbstractEntity {
  protected String name;

  protected LocalDateTime dateTime;

  protected int favoriteNumber;
}
