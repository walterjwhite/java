package com.walterjwhite.exchange;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

public class ExchangeServiceFactory implements Provider<ExchangeService> {
  private static final String EXCHANGE_SERVICE_URI_KEY = "ExchangeServiceURI";
  protected final ExchangeService exchangeService;

  @Inject
  public ExchangeServiceFactory(PrivateEmailAccount privateEmailAccount) {

    exchangeService = new ExchangeService(getExchangeVersion(privateEmailAccount));
    exchangeService.setCredentials(getExchangeCredentials(privateEmailAccount));

    try {
      exchangeService.autodiscoverUrl(getExchangeServiceUri(privateEmailAccount));
    } catch (Exception e) {
      throw new RuntimeException("Error configuring exchange service:", e);
    }
  }

  protected ExchangeCredentials getExchangeCredentials(PrivateEmailAccount privateEmailAccount) {
    return new WebCredentials(
        privateEmailAccount.getUsername(),
        privateEmailAccount.getPassword().getPlainText(),
        privateEmailAccount.getDomain());
  }

  private static String getExchangeServiceUri(PrivateEmailAccount privateEmailAccount) {
    return privateEmailAccount.getProvider().getSettings().get(EXCHANGE_SERVICE_URI_KEY);
  }

  private static ExchangeVersion getExchangeVersion(PrivateEmailAccount privateEmailAccount) {
    final String value =
        privateEmailAccount.getProvider().getSettings().get(ExchangeVersion.class.getName());

    if (value == null || value.isEmpty()) {
      return (ExchangeVersion.Exchange2010_SP2);
    }

    return (ExchangeVersion.valueOf(value));
  }

  @Override
  public ExchangeService get() {
    return (exchangeService);
  }
}
