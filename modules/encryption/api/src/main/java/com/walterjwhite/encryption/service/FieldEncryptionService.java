package com.walterjwhite.encryption.service;

import com.walterjwhite.encryption.enumeration.EncryptionType;
import java.lang.reflect.Field;

public interface FieldEncryptionService {
  void encrypt(Object e, Field field, EncryptionType encryptionType);

  void decrypt(Object e, Field field, EncryptionType encryptionType) throws Exception;
}
