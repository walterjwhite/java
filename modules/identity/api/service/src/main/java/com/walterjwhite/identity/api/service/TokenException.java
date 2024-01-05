package com.walterjwhite.identity.api.service;

public class TokenException extends RuntimeException {
  public TokenException(Throwable throwable) {
    super(throwable);
  }
}
