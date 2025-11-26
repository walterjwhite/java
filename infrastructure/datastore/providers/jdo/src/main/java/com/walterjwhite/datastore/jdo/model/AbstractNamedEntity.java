package com.walterjwhite.datastore.jdo.model;

import com.walterjwhite.datastore.api.model.Entity;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.COMPLETE_TABLE)
public abstract class AbstractNamedEntity implements Entity<String> {

  @PrimaryKey protected String name;

  @EqualsAndHashCode.Exclude protected String description;

  protected AbstractNamedEntity(String name) {
    this.name = name;
  }

  @Override
  public String getId() {
    return name;
  }
}
