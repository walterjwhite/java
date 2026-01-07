package com.walterjwhite.calendar.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import com.walterjwhite.file.api.model.File;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class CalendarAttachment extends AbstractEntity {
  @Column protected String serverId;

  @EqualsAndHashCode.Exclude @Column protected File file;
}
