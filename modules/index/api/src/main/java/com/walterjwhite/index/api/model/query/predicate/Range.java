package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class Range extends AbstractEntity {
  protected Number low;
  protected Number high;
}
