package com.walterjwhite.browser.modules.web.concept.api.model;

import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class RunningApplication extends TaskbarIcon {
  protected String name;

  // workspace-aware (sub-class)
  protected Workspace workspace;
}
