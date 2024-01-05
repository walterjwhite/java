package com.walterjwhite.index.service;

import com.walterjwhite.index.api.service.IndexBridgeService;
import java.io.Serializable;

public class DefaultIndexBridgeService implements IndexBridgeService {
  @Override
  public Serializable get(Serializable serializable, Integer id) {
    return serializable;
  }
}
