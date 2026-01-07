package com.walterjwhite.shell.api.model;

import com.walterjwhite.shell.api.enumeration.BatteryState;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class BatteryStatus {

  protected Node node;

  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected BatteryState batteryState;

  @EqualsAndHashCode.Exclude protected int batteryChargePercentage;

  @EqualsAndHashCode.Exclude protected ShellCommand shellCommand;

}
