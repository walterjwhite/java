package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jpa.model.AbstractNamedEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class JPANamedEntity extends AbstractNamedEntity {
}
