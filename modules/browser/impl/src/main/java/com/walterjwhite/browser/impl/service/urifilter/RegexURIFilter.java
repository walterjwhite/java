/*
package com.walterjwhite.browser.impl.service.urifilter;

import com.walterjwhite.browser.api.model.URIFilterConfiguration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexURIFilter extends AbstractURIFilter {
  protected final URIFilterConfiguration uriFilterConfiguration;
  protected final Pattern pattern;

  public RegexURIFilter(final URIFilterConfiguration uriFilterConfiguration) {
    this.uriFilterConfiguration = uriFilterConfiguration;
    // pattern = Pattern.compile(uriFilterConfiguration.getPattern(), Pattern.UNIX_LINES);
    pattern = Pattern.compile(uriFilterConfiguration.getPattern());
  }

  @Override
  public boolean doMatch(String uri) {
    final Matcher matcher = pattern.matcher(uri);
    return (matcher.matches());
  }
}
*/
