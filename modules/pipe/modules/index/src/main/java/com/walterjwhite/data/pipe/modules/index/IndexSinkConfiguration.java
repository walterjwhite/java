package com.walterjwhite.data.pipe.modules.index;

import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class IndexSinkConfiguration extends AbstractSinkConfiguration {
  protected IndexConfiguration indexConfiguration;

  public IndexSinkConfiguration() {
    super(IndexSink.class);
  }
}
