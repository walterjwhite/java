package com.walterjwhite.datastore.jpa.model;

import com.walterjwhite.datastore.api.model.Entity;
import jakarta.persistence.*;
import lombok.*;


@Embeddable
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public abstract class AbstractNamedEntity implements Entity<String> {

  @Id
  @Column(unique = true, nullable = false)
  protected String name;

  @EqualsAndHashCode.Exclude @Column @Lob protected String description;

  protected AbstractNamedEntity(String name) {
    this.name = name;
  }

  @Override
  public String getId() {
    return name;
  }
}
