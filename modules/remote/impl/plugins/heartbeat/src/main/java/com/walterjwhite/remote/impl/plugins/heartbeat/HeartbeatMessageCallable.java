package com.walterjwhite.remote.impl.plugins.heartbeat;


import com.walterjwhite.shell.api.model.*;

public class HeartbeatMessageCallable /*extends AbstractCallableJob<HeartbeatMessage, Void>*/ {
  protected HeartbeatMessage heartbeatMessage;

  protected void doDig(HeartbeatMessage heartbeatMessage) {
    if (heartbeatMessage.getDigRequest().getDigRequestIPAddresses() != null
        && heartbeatMessage.getDigRequest().getDigRequestIPAddresses().size() > 0) {
      showDigIPAddress(heartbeatMessage);
    }
  }

  protected void showDigIPAddress(HeartbeatMessage heartbeatMessage) {
    heartbeatMessage.getDigRequest().getDigRequestIPAddresses().get(0);
  }

  protected void doService(HeartbeatMessage heartbeatMessage) {
    for (ServiceStatus serviceStatus : heartbeatMessage.getServiceStatuses()) {

      if (serviceStatus.getBindAddressStates() != null
          && !serviceStatus.getBindAddressStates().isEmpty()) {
        for (BindAddressState addressState : serviceStatus.getBindAddressStates()) {
        }
      }
    }
  }

  protected void doInterface(HeartbeatMessage heartbeatMessage) {
    for (NetworkInterfaceState networkInterfaceState :
        heartbeatMessage.getNetworkInterfaceStates()) {

      if (networkInterfaceState.getIpAddressStates() != null
          && !networkInterfaceState.getIpAddressStates().isEmpty()) {
        for (IPAddressState addressState : networkInterfaceState.getIpAddressStates()) {
        }
      }
    }
  }

}
