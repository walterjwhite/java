package com.walterjwhite.infrastructure.inject.core.model;

import com.walterjwhite.property.api.property.ApplicationEnvironment;
import lombok.*;

import java.util.List;

@Builder
@ToString(doNotUseGetters = true)
@Getter
@Setter
public class ApplicationIdentifier {

  @NonNull
  protected ApplicationEnvironment applicationTargetEnvironment;

  @NonNull protected ApplicationEnvironment applicationEnvironment;

  @NonNull protected String applicationName;

  @NonNull protected String applicationVersion;

  @NonNull protected String scmVersion;

  @EqualsAndHashCode.Exclude @ToString.Exclude
  protected List<ApplicationSession> applicationSessions;
}
