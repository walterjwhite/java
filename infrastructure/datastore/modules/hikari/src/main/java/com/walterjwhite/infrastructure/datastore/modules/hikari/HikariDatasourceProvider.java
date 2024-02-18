package com.walterjwhite.infrastructure.datastore.modules.hikari;

import lombok.RequiredArgsConstructor;
import jakarta.inject.Provider;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@RequiredArgsConstructor
public class HikariDatasourceProvider implements Provider<HikariDataSource> {
    protected final HikariConfig hikariConfig;

    public HikariDataSource get() {
        return new HikariDataSource(hikariConfig);
    }
}
