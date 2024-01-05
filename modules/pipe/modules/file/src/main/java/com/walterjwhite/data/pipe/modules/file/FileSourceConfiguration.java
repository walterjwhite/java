package com.walterjwhite.data.pipe.modules.file;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class FileSourceConfiguration extends AbstractSourceConfiguration {
  protected String path;

  public FileSourceConfiguration(final String path) {
    super(FileSource.class);

    this.path = path;
  }
}
