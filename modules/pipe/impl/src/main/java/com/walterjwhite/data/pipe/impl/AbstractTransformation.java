package com.walterjwhite.data.pipe.impl;

import com.walterjwhite.data.pipe.api.transformation.AbstractTransformationConfiguration;
import com.walterjwhite.data.pipe.api.transformation.Transformation;

public abstract class AbstractTransformation<
        SourceRecordType,
        SinkRecordType,
        TransformationConfigurationType extends AbstractTransformationConfiguration>
    implements Transformation<SourceRecordType, SinkRecordType, TransformationConfigurationType> {
  protected TransformationConfigurationType transformationConfiguration;

  @Override
  public void configure(TransformationConfigurationType transformationConfiguration) {
    this.transformationConfiguration = transformationConfiguration;
    doConfigure();
  }

  protected abstract void doConfigure();
}
