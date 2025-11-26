package com.walterjwhite.shell.api.model;

import com.walterjwhite.shell.api.enumeration.BatteryRequestAction;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class BatteryRequest implements ShellCommandable {

  protected BatteryRequestAction action;

  protected ShellCommand shellCommand;

  @EqualsAndHashCode.Exclude protected BatteryStatus batteryStatus;

  @EqualsAndHashCode.Exclude protected int timeout;
}
