package com.walterjwhite.encryption.service;

import com.walterjwhite.encryption.model.EncryptionPolicy;

public interface DigestServiceProvider {
  DigestService get(EncryptionPolicy encryptionPolicy);
}
