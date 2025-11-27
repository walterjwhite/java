package com.walterjwhite.infrastructure.datastore.modules.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HikariDatasourceProvider implements Provider<HikariDataSource> {
  protected final HikariConfig hikariConfig;

  public HikariDataSource get() {
    return new HikariDataSource(hikariConfig);
  }
}
