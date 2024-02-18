package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jdo.model.AbstractNamedEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Data
@NoArgsConstructor
public class NamedEntity extends AbstractNamedEntity {
}
