package com.walterjwhite.browser.api.authentication.field;

import lombok.Data;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public abstract class AbstractFieldEntry {
  protected String fieldPairId;
}
