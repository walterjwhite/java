package com.walterjwhite.index.api.model;

public interface SearchOption {
  String getDescription();

  boolean enabledByDefault();

  void apply();
}
