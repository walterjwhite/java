package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.model.BatteryStatus;
import com.walterjwhite.shell.api.model.NetworkInterfaceState;
import com.walterjwhite.shell.api.model.ServiceStatus;
import com.walterjwhite.shell.api.model.dig.DigRequest;
import com.walterjwhite.shell.api.model.ping.PingRequest;
import com.walterjwhite.shell.api.model.traceroute.TracerouteRequest;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class HeartbeatMessage extends Message {
  protected BatteryStatus batteryStatus;

  protected Set<ServiceStatus> serviceStatuses = new HashSet<>();

  protected Set<NetworkInterfaceState> networkInterfaceStates = new HashSet<>();

  protected DigRequest digRequest;

  protected TracerouteRequest tracerouteRequest;
  protected PingRequest pingRequest;


}
