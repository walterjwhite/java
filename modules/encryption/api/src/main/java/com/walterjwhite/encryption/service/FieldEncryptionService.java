package com.walterjwhite.encryption.service;

import com.walterjwhite.encryption.enumeration.CryptoFunction;
import java.lang.reflect.Field;

public interface FieldEncryptionService {
  void encrypt(Object e, Field field, CryptoFunction cryptoFunction);

  void decrypt(Object e, Field field, CryptoFunction cryptoFunction) throws Exception;
}
