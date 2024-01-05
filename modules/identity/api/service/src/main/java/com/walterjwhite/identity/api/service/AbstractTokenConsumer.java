package com.walterjwhite.identity.api.service;

import lombok.RequiredArgsConstructor;

@Deprecated
@RequiredArgsConstructor
public abstract class AbstractTokenConsumer {
  protected final TokenService tokenService;

  public void execute(final Object argument) throws Exception {
    final String helpText = getHelpText(argument);

    try {
      final String token = tokenService.get(helpText);
      doExecute(argument, token);
      tokenService.onSuccess("success - " + helpText);
    } catch (Exception e) {
      tokenService.onException(e);
    }
  }

  protected abstract void doExecute(final Object argument, final String token);

  protected abstract String getHelpText(final Object argument);
}
