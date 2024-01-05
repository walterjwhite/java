package com.walterjwhite.identity.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Slf4j
@RequiredArgsConstructor
public class FallbackTokenService implements TokenService {
  protected final TokenService[] delegates;
  protected transient int index = 0;

  @Override
  public String get(String helpText) {
    do {
      try {
        return delegates[index].get(helpText);
      } catch (Exception e) {
        LOGGER.warn("Attempting next tokenService in chain", e);

        index++;
      }

    } while (index < delegates.length);

    throw new IllegalStateException("Unable to get token, no providers left");
  }

  @Override
  public void onSuccess(String message, Object... arguments) throws Exception {
    delegates[index].onSuccess(message, arguments);
  }

  @Override
  public void onException(Exception e) throws Exception {
    delegates[index].onException(e);
  }
}
