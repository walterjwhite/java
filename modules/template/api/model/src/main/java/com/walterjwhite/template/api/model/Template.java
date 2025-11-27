package com.walterjwhite.template.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class Template {

  protected String contents;


  public Template withContents(String contents) {
    this.contents = contents;
    return this;
  }
}
