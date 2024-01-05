package com.walterjwhite.template.api.service;

import com.walterjwhite.template.api.model.Template;

public interface TemplateService {
  String apply(Template template, Object... values);
}
