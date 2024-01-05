package com.walterjwhite.template.api.service;

import com.walterjwhite.template.api.model.ValueProvider;
import java.util.Map;

public interface ValueProviderService {
  Map<String, Object> getValueMap(ValueProvider valueProvider);
}
