package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jpa.model.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class JPAEntity extends AbstractEntity {
  @Column protected String name;

  @ToString.Exclude
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
  protected List<JPAReferenceEntity> references;

  public JPAEntity(final String name) {
    this.name = name;
  }
}
