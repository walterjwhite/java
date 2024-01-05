package com.walterjwhite.infrastructure.inject.core.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class ApplicationSession extends AbstractEntity {
  @NonNull protected ApplicationIdentifier applicationIdentifier;

  //  @NonNull
  //
  //
  //  protected Node node;
  @NonNull protected String nodeId;

  @NonNull protected LocalDateTime startDateTime;

  @EqualsAndHashCode.Exclude protected LocalDateTime endDateTime;
}
