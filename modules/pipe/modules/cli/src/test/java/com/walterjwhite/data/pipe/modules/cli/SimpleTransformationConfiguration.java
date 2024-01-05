package com.walterjwhite.data.pipe.modules.cli;

import com.walterjwhite.data.pipe.api.transformation.AbstractTransformationConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class SimpleTransformationConfiguration extends AbstractTransformationConfiguration {
  protected String from;
  protected String to;

  public SimpleTransformationConfiguration(String from, String to) {
    super(SimpleTestTransformation.class);
    this.from = from;
    this.to = to;
  }
}
