package com.walterjwhite.template.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class Template extends AbstractNamedEntity {

  protected String contents;

  public Template(String name) {
    super(name);
  }

  public Template withContents(String contents) {
    this.contents = contents;
    return this;
  }
}
