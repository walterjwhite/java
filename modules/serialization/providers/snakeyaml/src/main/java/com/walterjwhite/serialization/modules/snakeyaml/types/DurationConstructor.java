package com.walterjwhite.serialization.modules.snakeyaml.types;

import java.time.Duration;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.nodes.Node;

public class DurationConstructor extends AbstractConstruct {

  @Override
  public Object construct(Node node) {
    //        String value = (String) constructScalar(node);
    //        int position = val.indexOf('d');
    //        Integer a = Integer.parseInt(val.substring(0, position));
    //        Integer b = Integer.parseInt(val.substring(position + 1));

    return Duration.ofSeconds(Long.valueOf(""));
  }
}
