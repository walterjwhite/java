package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.service.RandomSequenceGenerator;
import org.apache.commons.text.RandomStringGenerator;

public class DefaultRandomSequenceGenerator implements RandomSequenceGenerator {
  @Override
  public String generate(int minLength, int maxLength) {
    RandomStringGenerator generator =
        new RandomStringGenerator.Builder()
            /*.withinRange('a', 'z')
            .withinRange('A', 'Z')
            .withinRange('0', '9')
            .withinRange(' ', '/')
            .withinRange(':', '@')
            .withinRange('[', '_')
            .withinRange('{', '~')*/
            .withinRange(' ', '~')
            .build();

    return generator.generate(getLength(minLength, maxLength));
  }

  protected int getLength(int minLength, int maxLength) {
    return (int) Math.round(Math.random() * maxLength + minLength);
  }

  @Override
  public String generate(int minLength, int maxLength, String characters) {
    throw new UnsupportedOperationException("NOT SUPPORTED");
  }
}
