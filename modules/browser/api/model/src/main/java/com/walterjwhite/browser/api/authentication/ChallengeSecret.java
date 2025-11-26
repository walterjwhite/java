package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.property.api.property.Secret;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class ChallengeSecret extends Secret {
  protected String questionText;
}
