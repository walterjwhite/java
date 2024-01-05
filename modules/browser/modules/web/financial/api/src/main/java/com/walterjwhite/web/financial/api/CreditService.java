package com.walterjwhite.web.financial.api;

import com.walterjwhite.browser.api.model.WebSession;

public interface CreditService {
  double getBalance(final WebSession webSession);

  void payBalance(WebSession webSession);
}
