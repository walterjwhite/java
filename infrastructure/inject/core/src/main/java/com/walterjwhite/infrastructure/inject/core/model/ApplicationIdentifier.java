package com.walterjwhite.infrastructure.inject.core.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.property.api.property.ApplicationEnvironment;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class ApplicationIdentifier extends AbstractEntity {

  @NonNull protected ApplicationEnvironment applicationTargetEnvironment;

  @NonNull protected ApplicationEnvironment applicationEnvironment;

  @NonNull protected String applicationName;

  @NonNull protected String applicationVersion;

  @NonNull protected String scmVersion;

  @EqualsAndHashCode.Exclude @ToString.Exclude
  protected List<ApplicationSession> applicationSessions = new ArrayList<>();
}
