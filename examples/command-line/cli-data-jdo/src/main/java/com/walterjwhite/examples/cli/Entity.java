package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;

@PersistenceCapable
@Data
@NoArgsConstructor
public class Entity extends AbstractEntity {
  @Column protected String name;

  public Entity(final String name) {
    this.name = name;
  }
}
