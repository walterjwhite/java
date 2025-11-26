package com.walterjwhite.data.pipe.api.session;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PersistenceCapable
public class PipeSessionConfiguration {
  protected String name;
  protected String description;

  protected List<PipeSessionGroupConfiguration> groups = new ArrayList<>();

  public PipeSessionConfiguration(String name, List<PipeSessionGroupConfiguration> groups) {
    this.name = name;

    if (groups != null && !groups.isEmpty()) {
      this.groups.addAll(groups);
    }
  }

  public PipeSessionConfiguration(
      String name, String description, List<PipeSessionGroupConfiguration> groups) {
    this(name, groups);
    this.description = description;
  }
}
