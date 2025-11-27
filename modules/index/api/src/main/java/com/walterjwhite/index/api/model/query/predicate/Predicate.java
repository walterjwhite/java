package com.walterjwhite.index.api.model.query.predicate;

import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class Predicate {

  protected boolean invert = false;

}
