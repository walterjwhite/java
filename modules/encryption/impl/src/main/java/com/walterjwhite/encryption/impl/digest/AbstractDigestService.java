package com.walterjwhite.encryption.impl.digest;

import com.walterjwhite.encryption.impl.EncryptionConfiguration;
import com.walterjwhite.encryption.service.DigestService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.io.FileUtils;

public abstract class AbstractDigestService implements DigestService {
  protected final EncryptionConfiguration encryptionConfiguration;

  public AbstractDigestService(EncryptionConfiguration encryptionConfiguration) {

    this.encryptionConfiguration = encryptionConfiguration;
  }

  @Override
  public boolean matches(java.io.File file, String expectedChecksum)
      throws IOException, NoSuchAlgorithmException {
    return (compute(file).equals(expectedChecksum));
  }

  @Override
  public boolean matches(java.io.File file, java.io.File checksumFile)
      throws IOException, NoSuchAlgorithmException {
    return (matches(file, getFromFile(checksumFile)));
  }

  public String getSignatureFromFile(final File file) throws IOException {
    return getFromFile(file);
  }

  protected static String getFromFile(final String filename) throws IOException {
    return (getFromFile(new java.io.File(filename)));
  }

  protected static String getFromFile(final java.io.File file) throws IOException {
    final String content = FileUtils.readLines(file, "UTF-8").get(0);
    return (content.split(" ")[1]);
  }

  @Override
  public String computeSignature(InputStream inputStream)
      throws IOException, NoSuchAlgorithmException {
    return (compute(inputStream));
  }

  //    public String hash(final byte[] data, int count) {
  //        throw new UnsupportedOperationException("Not yet implemented."));
  //
  //        //    final MessageDigest messageDigest =
  //        //            MessageDigest.getInstance(
  //        //
  // encryptionConfiguration.getEncryptionAlgorithm().getAlgorithmName());
  //        //
  //        //    byte[] input = plaintext.getBytes();
  //        //    for (int i = 0; i < encryptionConfiguration.getHashIterations(); i++) {
  //        //      input = messageDigest.digest(input);
  //        //    }
  //        //
  //        //    return (input);
  //    }
}
