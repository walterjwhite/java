package com.walterjwhite.data.pipe.api.transformation;

import java.util.function.Function;

public interface Transformation<
        SourceRecordType,
        SinkRecordType,
        TransformationConfigurationType extends AbstractTransformationConfiguration>
    extends Function<SourceRecordType, SinkRecordType> {
  void configure(TransformationConfigurationType transformationConfiguration);
}
