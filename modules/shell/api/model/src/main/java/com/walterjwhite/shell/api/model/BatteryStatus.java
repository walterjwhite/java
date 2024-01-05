package com.walterjwhite.shell.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.enumeration.BatteryState;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class BatteryStatus extends AbstractEntity {

  protected Node node;

  protected LocalDateTime dateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected BatteryState batteryState;

  @EqualsAndHashCode.Exclude protected int batteryChargePercentage;

  @EqualsAndHashCode.Exclude protected ShellCommand shellCommand;

  //  public BatteryStatus(
  //      Node node,
  //      BatteryState batteryState,
  //      int batteryChargePercentage,
  //      ShellCommand shellCommand) {
  //
  //
  //    this.node = node;
  //    this.batteryState = batteryState;
  //    this.batteryChargePercentage = batteryChargePercentage;
  //    this.shellCommand = shellCommand;
  //  }
}
