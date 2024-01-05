package com.walterjwhite.browser.data;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// this is unused
@Deprecated
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
public class BrowserScript extends AbstractNamedEntity {
  protected String script;

  public BrowserScript(String name, String script) {
    super(name);
    this.script = script;
  }
}
