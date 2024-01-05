package com.walterjwhite.template.providers.stringtemplate.service;

import com.walterjwhite.file.impl.service.AbstractTemplateService;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.template.providers.stringtemplate.property.StringTemplateDelimeterStartChar;
import com.walterjwhite.template.providers.stringtemplate.property.StringTemplateDelimeterStopChar;
import java.util.Map;
import jakarta.inject.Inject;
import org.stringtemplate.v4.ST;

public class StringTemplateTemplateService extends AbstractTemplateService {
  protected final char delimeterStartChar;
  protected final char delimeterStopChar;

  @Inject
  public StringTemplateTemplateService(
      @Property(StringTemplateDelimeterStartChar.class) char delimeterStartChar,
      @Property(StringTemplateDelimeterStopChar.class) char delimeterStopChar) {
    this.delimeterStartChar = delimeterStartChar;
    this.delimeterStopChar = delimeterStopChar;
  }

  protected String doFormat(String template, final Map<String, Object> valueMap) {
    ST stringTemplate = new ST(template, delimeterStartChar, delimeterStopChar);
    setContextualData(stringTemplate, valueMap);
    return stringTemplate.render();
  }

  protected void setContextualData(final ST st, final Map<String, Object> valueMap) {
    for (final String key : valueMap.keySet()) st.add(key, valueMap.get(key));
  }
}
