package com.walterjwhite.serialization.modules.snakeyaml.types;

import java.time.Duration;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;

public class DurationRepresenter implements Represent {
  @Override
  public Node representData(final Object data) {
    final Duration duration = (Duration) data;
    String value = Long.toString(duration.toSeconds());
    //        return Representer.representScalar(new Tag("!!" + Duration.class.getName()), value);

    return new ScalarNode(
        new Tag("!!" + Duration.class.getName()),
        value,
        null,
        null,
        DumperOptions.ScalarStyle.PLAIN);
  }
}
