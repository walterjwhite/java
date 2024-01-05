package com.walterjwhite.data.pipe.impl;

import com.walterjwhite.data.pipe.api.transformation.AbstractTransformationConfiguration;
import com.walterjwhite.data.pipe.api.transformation.Transformation;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DefaultTransformationChain implements Function {
  protected final List<Transformation> transformations = new ArrayList<>();

  public DefaultTransformationChain(
      List<AbstractTransformationConfiguration> transformationConfigurations) {

    for (final AbstractTransformationConfiguration transformationConfiguration :
        transformationConfigurations) {
      Transformation transformation =
          ApplicationHelper.getApplicationInstance()
              .getInjector()
              .getInstance(transformationConfiguration.getTransformationClass());
      transformation.configure(transformationConfiguration);
      transformations.add(transformation);
    }
  }

  @Override
  public Object apply(Object o) {
    for (Transformation transformation : transformations) o = transformation.apply(o);

    return o;
  }
}
