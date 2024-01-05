package com.walterjwhite.file.impl.service;

import com.walterjwhite.template.api.model.Template;
import com.walterjwhite.template.api.service.TemplateService;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTemplateService implements TemplateService {
  @Override
  public String apply(Template template, Object... values) {
    return doFormat(template.getContents(), prepareContextual(values));
  }

  protected Map<String, Object> prepareContextual(Object... values) {
    final Map<String, Object> valueMap = new HashMap<>();
    if (values != null && values.length > 0) {
      for (final Object value : values) valueMap.put(value.getClass().getSimpleName(), value);
    }

    return valueMap;
  }

  protected abstract String doFormat(String template, final Map<String, Object> valueMap);
}
