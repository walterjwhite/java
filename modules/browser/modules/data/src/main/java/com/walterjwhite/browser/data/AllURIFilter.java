/*
package com.walterjwhite.browser.impl.service.urifilter;

import com.walterjwhite.browser.api.model.URIFilterConfiguration;
import com.walterjwhite.browser.api.service.URIFilter;
import com.walterjwhite.datastore.api.repository.Repository;
import java.util.HashSet;
import java.util.Set;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class AllURIFilter implements URIFilter {
  protected final Provider<Repository> repositoryProvider;
  protected final Set<RegexURIFilter> regexURIFilters = new HashSet<>();
  protected boolean initialized = false;

  @Inject
  public AllURIFilter(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    //    setup();
  }
  //  @PostConstruct
  protected void setup() {
    for (URIFilterConfiguration uriFilterConfiguration :
        repositoryProvider.get().findAll(URIFilterConfiguration.class)) {
      regexURIFilters.add(new RegexURIFilter(uriFilterConfiguration));
    }
  }

  @Override
  public boolean matches(String uri) {
    if (!initialized) {
      setup();
    }
    for (RegexURIFilter regexURIFilter : regexURIFilters) {
      // exclude this URI
      if (regexURIFilter.matches(uri)) {
        return true;
      }
    }
    return false;
  }
}
*/
