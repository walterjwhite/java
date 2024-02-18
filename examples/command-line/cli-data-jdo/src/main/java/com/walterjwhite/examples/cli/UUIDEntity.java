package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jdo.model.AbstractUUIDEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Data
@NoArgsConstructor
public class UUIDEntity extends AbstractUUIDEntity {
}
