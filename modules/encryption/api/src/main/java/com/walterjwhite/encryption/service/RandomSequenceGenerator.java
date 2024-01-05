package com.walterjwhite.encryption.service;

public interface RandomSequenceGenerator {
  String generate(int minLength, int maxLength);

  String generate(int minLength, int maxLength, String characters);
}
