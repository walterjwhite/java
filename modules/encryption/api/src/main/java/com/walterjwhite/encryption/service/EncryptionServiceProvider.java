package com.walterjwhite.encryption.service;

import com.walterjwhite.encryption.model.EncryptionPolicy;

public interface EncryptionServiceProvider {
  EncryptionService get(EncryptionPolicy encryptionPolicy);
}
