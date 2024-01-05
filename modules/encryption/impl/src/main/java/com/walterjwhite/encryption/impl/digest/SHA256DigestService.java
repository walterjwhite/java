package com.walterjwhite.encryption.impl.digest;

import com.walterjwhite.encryption.impl.EncryptionConfiguration;
import java.io.*;
import org.apache.commons.codec.digest.DigestUtils;

public class SHA256DigestService extends AbstractDigestService {
  public SHA256DigestService(EncryptionConfiguration encryptionConfiguration) {
    super(encryptionConfiguration);
  }

  @Override
  public String compute(File file) throws IOException {
    return (DigestUtils.sha256Hex(new BufferedInputStream(new FileInputStream(file))));
  }

  @Override
  public String compute(InputStream inputStream) throws IOException {
    return (DigestUtils.sha256Hex(inputStream));
  }
}
