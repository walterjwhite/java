package com.walterjwhite.browser.modules.web.concept.api.model;

import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class Workspace {
  protected TaskbarIcon[] taskbarIcons;
}
