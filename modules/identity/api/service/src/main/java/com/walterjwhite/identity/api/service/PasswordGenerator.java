package com.walterjwhite.identity.api.service;

import com.walterjwhite.identity.api.model.password.CharacterSetConfiguration;
import com.walterjwhite.identity.api.model.password.PasswordPolicy;
import java.util.Random;
import org.passay.CharacterRule;

public interface PasswordGenerator {
  org.passay.PasswordGenerator PASSWORD_GENERATOR = new org.passay.PasswordGenerator();

  static String generate(final PasswordPolicy passwordPolicy) {
    return PASSWORD_GENERATOR.generatePassword(getLength(passwordPolicy), build(passwordPolicy));
  }

  private static CharacterRule[] build(final PasswordPolicy passwordPolicy) {
    final CharacterRule[] characterRules =
        new CharacterRule[passwordPolicy.getCharacterSetConfigurations().length];
    for (int i = 0; i < characterRules.length; i++) {
      final CharacterSetConfiguration characterSetConfiguration =
          passwordPolicy.getCharacterSetConfigurations()[i];
      characterRules[i] =
          characterSetConfiguration.getCharacterClass().get(characterSetConfiguration);
    }

    return characterRules;
  }

  private static int getLength(final PasswordPolicy passwordPolicy) {
    final Random random = new Random();
    return random
        .ints(passwordPolicy.getMinimumLength(), passwordPolicy.getMaximumLength())
        .findFirst()
        .getAsInt();
  }
}
