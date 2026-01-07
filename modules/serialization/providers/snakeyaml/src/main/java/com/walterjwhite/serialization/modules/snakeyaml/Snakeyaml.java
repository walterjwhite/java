package com.walterjwhite.serialization.modules.snakeyaml;

import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.serialization.api.service.YAML;
import jakarta.inject.Inject;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class Snakeyaml implements SerializationService, YAML {
  protected transient Yaml yaml = new Yaml(new MessageRepresenter(), new DumperOptions());

  @Override
  public void serialize(@Deprecated Serializable data, OutputStream outputStream) {
    yaml.dump(data, new OutputStreamWriter(outputStream));
  }

  @Override
  public <EntityType> EntityType deserialize(
      InputStream inputStream, Class<EntityType> entityClass) {
    return yaml.loadAs(inputStream, entityClass);
  }

  @Override
  public Serializable deserialize(InputStream inputStream) {
    return yaml.load(inputStream);
  }
}
