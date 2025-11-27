package com.walterjwhite.transform.hash;

import org.apache.commons.codec.digest.DigestUtils;

public enum HashType {
  SHA256 {
    public String hash(final String input) {
      return DigestUtils.sha256Hex(input);
    }
  },
  SHA384 {
    public String hash(final String input) {
      return DigestUtils.sha384Hex(input);
    }
  },
  SHA512 {
    public String hash(final String input) {
      return DigestUtils.sha512Hex(input);
    }
  };

  public abstract String hash(final String input);
}
