package com.walterjwhite.metrics.modules.prometheus;

import com.netflix.spectator.atlas.AtlasConfig;

public class DefaultAtlasConfig implements AtlasConfig {
  @Override
  public String uri() {
    return "http://localhost:7101/api/v1/publish";
  }

  @Override
  public String get(String k) {
    return null;
  }
}
