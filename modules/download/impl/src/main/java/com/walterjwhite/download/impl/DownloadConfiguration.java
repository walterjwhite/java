package com.walterjwhite.download.impl;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class DownloadConfiguration {
  protected String downloadPath;
}
