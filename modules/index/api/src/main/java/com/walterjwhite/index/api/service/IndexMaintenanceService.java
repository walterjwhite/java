package com.walterjwhite.index.api.service;

import java.io.IOException;

public interface IndexMaintenanceService {
  void create(Class<?> entityClass) throws IOException;

  void delete(Class<?> entityClass) throws IOException;
}
