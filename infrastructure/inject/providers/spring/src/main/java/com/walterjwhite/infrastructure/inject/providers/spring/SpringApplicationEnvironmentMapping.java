package com.walterjwhite.infrastructure.inject.providers.spring;

import com.walterjwhite.property.api.property.ApplicationEnvironment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpringApplicationEnvironmentMapping {
  Development(ApplicationEnvironment.Development),
  Testing(ApplicationEnvironment.Testing),
  Production(ApplicationEnvironment.Production);

  private final ApplicationEnvironment applicationEnvironment;

  public static SpringApplicationEnvironmentMapping getFromApplicationEnvironment(
      ApplicationEnvironment applicationEnvironment) {
    for (final SpringApplicationEnvironmentMapping springApplicationEnvironmentMapping : values()) {
      if (springApplicationEnvironmentMapping
          .getApplicationEnvironment()
          .equals(applicationEnvironment)) return springApplicationEnvironmentMapping;
    }

    throw new IllegalArgumentException(applicationEnvironment + " is not currently mapped.");
  }
}
