package com.walterjwhite.browser.modules.web;

import com.walterjwhite.browser.api.authentication.FieldProcessor;
import com.walterjwhite.browser.api.authentication.field.FieldPair;
import com.walterjwhite.browser.api.authentication.field.FieldSecret;
import com.walterjwhite.identity.api.service.TokenService;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class TokenFieldProcessor implements FieldProcessor {
  protected final TokenService tokenService;

  @Override
  @SneakyThrows
  public String get(FieldPair fieldPair, FieldSecret fieldSecret) {
    return fieldSecret.getSecret().getValue() + tokenService.get("");
  }
}
