package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jpa.model.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class JPAEntity extends AbstractEntity {
  @Column protected String name;

  @ToString.Exclude
  @OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
  protected List<JPAReferenceEntity> references;

  public JPAEntity(final String name) {
    this.name = name;
  }
}
