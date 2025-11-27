package com.walterjwhite.encryption.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.io.*;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

@RequiredArgsConstructor
public enum DigestAlgorithm implements ConfigurableProperty {
  MD5(DigestUtils::md5Hex) {
    @Override
    public String compute(InputStream inputStream) throws IOException {
      return DigestUtils.md5Hex(inputStream);
    }
  },
  SHA_1(DigestUtils::sha1Hex) {
    @Override
    public String compute(InputStream inputStream) throws IOException {
      return DigestUtils.sha1Hex(inputStream);
    }
  },
  @DefaultValue
  SHA_256(DigestUtils::sha256Hex) {
    @Override
    public String compute(InputStream inputStream) throws IOException {
      return DigestUtils.sha256Hex(inputStream);
    }
  },
  SHA_384(DigestUtils::sha3_384Hex) {
    @Override
    public String compute(InputStream inputStream) throws IOException {
      return DigestUtils.sha384Hex(inputStream);
    }
  },
  SHA_512(DigestUtils::sha3_512Hex) {
    @Override
    public String compute(InputStream inputStream) throws IOException {
      return DigestUtils.sha512Hex(inputStream);
    }
  };

  private final Function<String, String> hashFunction;

  public String getAlgorithmName() {
    return (name().replace("_", "-"));
  }

  public String compute(final String input) {
    return hashFunction.apply(input);
  }

  public abstract String compute(final InputStream inputStream) throws IOException;

  public String compute(final File file) throws IOException {
    return compute(new BufferedInputStream(new FileInputStream(file)));
  }

  public boolean matches(final File file, final String checksum) throws IOException {
    return compute(file).equals(checksum);
  }

  public boolean matches(final File file, final File checksumFile) throws IOException {
    return compute(file).equals(getFromFile(checksumFile));
  }

  public static String getFromFile(final java.io.File file) throws IOException {
    final String content = FileUtils.readLines(file, "UTF-8").get(0);
    return (content.split(" ")[1]);
  }

  public String getSignatureFromFile(final File file) throws IOException {
    return getFromFile(file);
  }
}
