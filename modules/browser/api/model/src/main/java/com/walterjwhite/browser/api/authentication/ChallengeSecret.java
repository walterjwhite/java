package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.property.api.property.Secret;
import lombok.Data;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class ChallengeSecret extends Secret {

  protected String questionText;
}
