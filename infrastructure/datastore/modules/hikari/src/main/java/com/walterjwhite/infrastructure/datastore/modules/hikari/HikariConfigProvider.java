package com.walterjwhite.infrastructure.datastore.modules.hikari;

import com.walterjwhite.infrastructure.datastore.modules.hikari.property.*;
import com.walterjwhite.property.api.annotation.Property;
import com.zaxxer.hikari.HikariConfig;
import jakarta.inject.Provider;
import java.util.Properties;

public class HikariConfigProvider implements Provider<HikariConfig> {
  protected final String hikariDriverClassName;
  protected final String hikariDataSourceClassName;
  protected final String hikariJDBCUrl;
  protected final String hikariJDBCUsername;
  protected final String hikariJDBCPassword;

  public HikariConfigProvider(
      @Property(HikariJDBCDriverClassName.class) final String hikariDriverClassName,
      @Property(HikariJDBCDataSourceClassName.class) final String hikariDataSourceClassName,
      @Property(HikariJDBCUrl.class) final String hikariJDBCUrl,
      @Property(HikariJDBCUsername.class) final String hikariJDBCUsername,
      @Property(HikariJDBCPassword.class) final String hikariJDBCPassword) {
    this.hikariDriverClassName = hikariDriverClassName;
    this.hikariDataSourceClassName = hikariDataSourceClassName;
    this.hikariJDBCUrl = hikariJDBCUrl;
    this.hikariJDBCUsername = hikariJDBCUsername;
    this.hikariJDBCPassword = hikariJDBCPassword;
  }

  public HikariConfig get() {
    Properties properties = new Properties();
    properties.setProperty("dataSourceClassName", hikariDataSourceClassName);
    properties.setProperty("driverClassName", hikariDriverClassName);
    properties.setProperty("dataSource.user", hikariJDBCUsername);
    properties.setProperty("dataSource.password", hikariJDBCPassword);

    return new HikariConfig(properties);
  }
}
