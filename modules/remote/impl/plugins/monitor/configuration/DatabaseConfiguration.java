package com.walterjwhite.remote.impl.service.configuration;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class DatabaseConfiguration {

  protected String databaseUri;
  protected String databaseUsername;
  @ToString.Exclude protected String databasePassword;
  protected String databaseDriver;
}
