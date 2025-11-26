package com.walterjwhite.index.api.model.index;

import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
public class IndexEntityType {

  protected Index index;

}
