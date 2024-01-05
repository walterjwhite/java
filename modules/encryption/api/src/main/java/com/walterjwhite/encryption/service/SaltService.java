package com.walterjwhite.encryption.service;

public interface SaltService {
  byte[] generate();

  byte[] generate(int length);
}
