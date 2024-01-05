package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.browser.api.authentication.ChallengeSecret;
import com.walterjwhite.infrastructure.inject.core.Secret;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class FieldChallengeSecret extends AbstractFieldSecret {
  protected ChallengeSecret[] challengeSecrets;

  @Override
  public Secret getSecret() {
    final FieldPair fieldPair = null;

    fieldPair.getLabelLocator();
    final String questionText = null;

    for (final ChallengeSecret challengeSecret : challengeSecrets) {
      if (questionText.equalsIgnoreCase(challengeSecret.getQuestionText())) {
        return challengeSecret;
      }
    }

    throw new IllegalArgumentException("Challenge not found.");
  }
}
