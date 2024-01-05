package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.infrastructure.inject.core.Secret;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class ChallengeSecret extends Secret {
  // used to match the question on screen
  protected String questionText;
}
