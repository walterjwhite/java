package com.walterjwhite.ip.impl.service;

import com.walterjwhite.ip.api.service.PublicIPLookupService;
import java.util.Properties;
import jakarta.inject.Provider;

public class PublicIPLookupServiceProvider implements Provider<PublicIPLookupService> {
  protected final PublicIPLookupServiceConfiguration publicIPLookupServiceConfiguration;

  public PublicIPLookupServiceProvider(final Properties properties) {

    publicIPLookupServiceConfiguration = new PublicIPLookupServiceConfiguration();
    publicIPLookupServiceConfiguration.setProvider(
        properties.getProperty("PUBLIC_IP_LOOKUP_SERVICE_PROVIDER"));
  }

  public PublicIPLookupServiceProvider() {

    publicIPLookupServiceConfiguration = new PublicIPLookupServiceConfiguration();
  }

  @Override
  public PublicIPLookupService get() {
    if (publicIPLookupServiceConfiguration.getProvider() == null
        || publicIPLookupServiceConfiguration.getProvider().isEmpty())
      //      return (new JSONIPPublicIPLookupService());
      return (new ApiIPifyPublicIPLookupService());

    if (publicIPLookupServiceConfiguration.getProvider().equalsIgnoreCase("APIIPIFY")) {
      return (new ApiIPifyPublicIPLookupService());
    }
    if (publicIPLookupServiceConfiguration.getProvider().equalsIgnoreCase("FREEGEO")) {
      return (new FreeGeoIPPublicIPLookupService());
    }
    if (publicIPLookupServiceConfiguration.getProvider().equalsIgnoreCase("HTTPBIN")) {
      return (new HttpBinPublicIPLookupService());
    }

    // return (new JSONIPPublicIPLookupService());
    return (new ApiIPifyPublicIPLookupService());
  }
}
