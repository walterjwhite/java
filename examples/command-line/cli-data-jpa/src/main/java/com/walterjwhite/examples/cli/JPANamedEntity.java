package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jpa.model.AbstractNamedEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class JPANamedEntity extends AbstractNamedEntity {}
