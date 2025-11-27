package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jpa.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class JPAReferenceEntity extends AbstractEntity {
  @Column protected String color;

  @ManyToOne @JoinColumn protected JPAEntity parent;

  public JPAReferenceEntity(final String color, final JPAEntity parent) {
    this.color = color;
    this.parent = parent;
  }
}
