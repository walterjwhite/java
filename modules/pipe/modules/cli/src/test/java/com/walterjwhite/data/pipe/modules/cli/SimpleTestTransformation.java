package com.walterjwhite.data.pipe.modules.cli;

import com.walterjwhite.data.pipe.impl.AbstractTransformation;

public class SimpleTestTransformation
    extends AbstractTransformation<String[], String[], SimpleTransformationConfiguration> {
  @Override
  protected void doConfigure() {}

  @Override
  public String[] apply(String[] strings) {
    final String[] output = new String[strings.length];
    for (int i = 0; i < strings.length; i++)
      output[i] =
          strings[i].replace(
              transformationConfiguration.getFrom(), transformationConfiguration.getTo());

    return (output);
  }
}
