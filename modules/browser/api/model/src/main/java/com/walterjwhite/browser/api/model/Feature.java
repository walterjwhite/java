package com.walterjwhite.browser.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class Feature {
  protected String name;
  protected BrowserActionInstance[] actions;
}
