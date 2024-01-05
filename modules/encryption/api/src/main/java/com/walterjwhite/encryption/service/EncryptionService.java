package com.walterjwhite.encryption.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface EncryptionService {
  void encrypt(InputStream inputStream, OutputStream outputStream) throws IOException;

  OutputStream getEncryptionStream(OutputStream outputStream);

  void decrypt(InputStream cipherStream, OutputStream plaintextStream) throws IOException;

  InputStream getDecryptionStream(InputStream inputStream);
}
