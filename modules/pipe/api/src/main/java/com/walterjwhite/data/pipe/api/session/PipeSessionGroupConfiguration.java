package com.walterjwhite.data.pipe.api.session;

import com.walterjwhite.data.pipe.api.DataPipeConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@PersistenceCapable
@NoArgsConstructor
public class PipeSessionGroupConfiguration {
  protected String name;
  protected String description;
  protected List<DataPipeConfiguration> dataPipeConfigurations = new ArrayList<>();

  public PipeSessionGroupConfiguration(
      String name, List<DataPipeConfiguration> dataPipeConfigurations) {
    this.name = name;
    this.dataPipeConfigurations = dataPipeConfigurations;
  }

  public PipeSessionGroupConfiguration(
      String name, String description, List<DataPipeConfiguration> dataPipeConfigurations) {
    this(name, dataPipeConfigurations);
    this.description = description;
  }
}
