package com.walterjwhite.serialization.modules.snakeyaml.types;

import java.time.Duration;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.nodes.Node;

public class DurationConstructor extends AbstractConstruct {

  @Override
  public Object construct(Node node) {

    return Duration.ofSeconds(Long.valueOf(""));
  }
}
