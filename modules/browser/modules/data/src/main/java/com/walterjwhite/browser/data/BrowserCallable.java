package com.walterjwhite.browser.data;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.NoArgsConstructor;

/** This mirrors the Job API ... */
// this is unused
@Deprecated
@NoArgsConstructor
@PersistenceCapable
public class BrowserCallable extends AbstractNamedEntity {

  public BrowserCallable(String browserCallableClassName) {
    super(browserCallableClassName);
  }
}
