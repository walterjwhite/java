package com.walterjwhite.file.impl.service;

import com.google.inject.AbstractModule;
import com.walterjwhite.file.api.service.FileEntityRepository;

public class DefaultFileStorageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(FileEntityRepository.class);
  }
}
