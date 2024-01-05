package com.walterjwhite.data.pipe.modules.index;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class IndexSourceConfiguration extends AbstractSourceConfiguration {
  protected IndexConfiguration indexConfiguration;

  public IndexSourceConfiguration() {
    super(IndexSource.class);
  }
}
