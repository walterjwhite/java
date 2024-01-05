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

/**
 * Capture the same information that would be reported through sysstatus: upower -d (battery level /
 * state) services / status / ports listening on ARP DIG google.com
 */
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class HeartbeatMessage extends Message {
  protected BatteryStatus batteryStatus;

  //
  //
  //
  //
  protected Set<ServiceStatus> serviceStatuses = new HashSet<>();

  protected Set<NetworkInterfaceState> networkInterfaceStates = new HashSet<>();

  protected DigRequest digRequest;

  protected TracerouteRequest tracerouteRequest;
  protected PingRequest pingRequest;


  public HeartbeatMessage(
      int timeToLive,
      final BatteryStatus batteryStatus,
      final Set<ServiceStatus> serviceStatuses,
      final DigRequest digRequest,
      final TracerouteRequest tracerouteRequest,
      final PingRequest pingRequest,
      final Set<NetworkInterfaceState> networkInterfaceStates) {
    super(timeToLive);

    this.batteryStatus = batteryStatus;

    if (serviceStatuses != null && !serviceStatuses.isEmpty())
      this.serviceStatuses.addAll(serviceStatuses);

    this.digRequest = digRequest;
    this.pingRequest = pingRequest;
    this.tracerouteRequest = tracerouteRequest;

    if (networkInterfaceStates != null && !networkInterfaceStates.isEmpty())
      this.networkInterfaceStates = networkInterfaceStates;
  }

  /** Required for de-serialization. */
  public HeartbeatMessage() {}
}
