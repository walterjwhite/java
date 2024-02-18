package com.walterjwhite.browser.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class Feature {
  protected String name;
  protected BrowserActionInstance[] actions;
}
