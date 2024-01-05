package com.walterjwhite.shell.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class ShellCommandEnvironmentProperty extends AbstractEntity {

  protected ShellCommand shellCommand;

  protected String key;

  @EqualsAndHashCode.Exclude protected String value;
}
