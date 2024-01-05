package com.walterjwhite.encryption.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

public interface DigestService {
  String compute(final File file) throws IOException;

  String compute(final InputStream inputStream) throws IOException;

  // Uses iv + key to "sign" the signature of the data
  String computeSignature(final InputStream inputStream)
      throws IOException, NoSuchAlgorithmException;

  boolean matches(final File file, final String expectedChecksum)
      throws IOException, NoSuchAlgorithmException;

  boolean matches(final File file, final File checksumFile)
      throws IOException, NoSuchAlgorithmException;

  String getSignatureFromFile(final File file) throws IOException;
}
