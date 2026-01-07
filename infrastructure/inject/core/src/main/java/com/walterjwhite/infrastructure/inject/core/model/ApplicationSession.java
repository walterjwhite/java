package com.walterjwhite.infrastructure.inject.core.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class ApplicationSession {
  @NonNull protected ApplicationIdentifier applicationIdentifier;

  @NonNull protected String nodeId;

  @NonNull protected LocalDateTime startDateTime;

  @EqualsAndHashCode.Exclude protected LocalDateTime endDateTime;
}
