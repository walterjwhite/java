package com.walterjwhite.infrastructure.inject.core.helper;

import com.walterjwhite.infrastructure.inject.core.model.ApplicationIdentifier;
import com.walterjwhite.infrastructure.inject.core.model.ApplicationSession;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationInstanceBuilder {
  public static ApplicationSession build() {
    return new ApplicationSession(
        new ApplicationIdentifier(
            ApplicationHelper.getApplicationTargetEnvironment(),
            ApplicationHelper.getApplicationEnvironment(),
            ApplicationHelper.getApplicationName(),
            ApplicationHelper.getImplementationVersion(),
            ApplicationHelper.getSCMVersion()),
        ApplicationHelper.getNodeId(),
        LocalDateTime.now());
  }
}
