package com.walterjwhite.browser.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class Feature {
  protected String name;
  protected BrowserActionInstance[] actions;
}
