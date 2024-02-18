package com.walterjwhite.encryption.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface DigestService {

  boolean matches(final File file, final File checksumFile)
      throws IOException, NoSuchAlgorithmException;

  String getSignatureFromFile(final File file) throws IOException;
}
