package com.walterjwhite.data.pipe.modules.index;

import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.index.Index;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class IndexConfiguration {
  protected Index index;
  protected EntityType entityType;
}
