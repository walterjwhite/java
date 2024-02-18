package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Data
@NoArgsConstructor
public class Entity extends AbstractEntity {
  @Column protected String name;

  public Entity(final String name) {
    this.name = name;
  }
}
