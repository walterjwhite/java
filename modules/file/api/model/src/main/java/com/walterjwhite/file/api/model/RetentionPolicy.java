package com.walterjwhite.file.api.model;

import java.time.Duration;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class RetentionPolicy {
  protected Duration retentionDuration;
}
