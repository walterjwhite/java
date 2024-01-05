package com.walterjwhite.data.pipe.api.source;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import java.io.Serializable;

// see: Java 8 Supplier lambda
public interface Source<
        RecordType extends Serializable,
        SourceConfigurationType extends AbstractSourceConfiguration>
    extends Iterable<RecordType> {
  void close() throws Exception;

  void configure(SourceConfigurationType sourceConfiguration);
}
