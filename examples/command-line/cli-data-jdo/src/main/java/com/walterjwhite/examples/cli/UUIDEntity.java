package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jdo.model.AbstractUUIDEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;

@PersistenceCapable
@Data
@NoArgsConstructor
public class UUIDEntity extends AbstractUUIDEntity {}
