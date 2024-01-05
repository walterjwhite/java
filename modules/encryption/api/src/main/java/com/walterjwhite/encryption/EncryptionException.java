package com.walterjwhite.encryption;

public class EncryptionException extends RuntimeException {
  public EncryptionException(Exception e) {
    super(e);
  }
}
