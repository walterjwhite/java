package com.walterjwhite.browser.data;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

// for subclasses
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class URIFilterConfiguration extends AbstractNamedEntity {
  protected boolean caseInsensitive;

  protected String pattern;

  public URIFilterConfiguration(String pattern) {

    this.pattern = pattern;
  }
}
