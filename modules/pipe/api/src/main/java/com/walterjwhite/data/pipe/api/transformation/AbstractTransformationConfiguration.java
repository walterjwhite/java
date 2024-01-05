package com.walterjwhite.data.pipe.api.transformation;

public abstract class AbstractTransformationConfiguration {
  protected final Class<? extends Transformation> transformationClass;

  protected AbstractTransformationConfiguration(
      Class<? extends Transformation> transformationClass) {
    this.transformationClass = transformationClass;
  }

  public Class<? extends Transformation> getTransformationClass() {
    return transformationClass;
  }
}
