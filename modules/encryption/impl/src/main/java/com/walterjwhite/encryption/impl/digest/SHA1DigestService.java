package com.walterjwhite.encryption.impl.digest;

import com.walterjwhite.encryption.impl.EncryptionConfiguration;
import java.io.*;
import org.apache.commons.codec.digest.DigestUtils;

public class SHA1DigestService extends AbstractDigestService {
  public SHA1DigestService(EncryptionConfiguration encryptionConfiguration) {
    super(encryptionConfiguration);
  }

  @Override
  public String compute(File file) throws IOException {
    return (DigestUtils.sha1Hex(new BufferedInputStream(new FileInputStream(file))));
  }

  @Override
  public String compute(InputStream inputStream) throws IOException {
    return (DigestUtils.sha1Hex(inputStream));
  }
}
