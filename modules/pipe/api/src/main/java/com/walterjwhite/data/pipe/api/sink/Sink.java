package com.walterjwhite.data.pipe.api.sink;

import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import java.io.Serializable;
import java.util.function.Consumer;

public interface Sink<
        RecordType extends Serializable, SinkConfigurationType extends AbstractSinkConfiguration>
    extends Consumer<RecordType> {
  void close() throws Exception;

  void configure(SinkConfigurationType sourceConfiguration);
}
