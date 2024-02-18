package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.browser.api.authentication.ChallengeSecret;
import com.walterjwhite.property.api.property.Secret;
import lombok.Data;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class FieldSecretChallenge extends AbstractFieldEntry implements FieldSecret {
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
