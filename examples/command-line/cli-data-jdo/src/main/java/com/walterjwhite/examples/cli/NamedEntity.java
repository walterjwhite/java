package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.jdo.model.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;

@PersistenceCapable
@Data
@NoArgsConstructor
public class NamedEntity extends AbstractNamedEntity {}
