package com.walterjwhite.remote.impl.plugins.heartbeat;

// import com.walterjwhite.queue.api.queuedJob.AbstractCallableJob;

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
      // ("service status:" + serviceStatus.getService().getName());

      if (serviceStatus.getBindAddressStates() != null
          && !serviceStatus.getBindAddressStates().isEmpty()) {
        for (BindAddressState addressState : serviceStatus.getBindAddressStates()) {
          //          (
          //              "service status: (ipAddress):"
          //                  + addressState.getBindAddress().getProtocol()
          //                  + "@"
          //                  + addressState.getBindAddress().getIpAddress().getAddress());
        }
      }
    }
  }

  protected void doInterface(HeartbeatMessage heartbeatMessage) {
    for (NetworkInterfaceState networkInterfaceState :
        heartbeatMessage.getNetworkInterfaceStates()) {
      //      (
      //          "received heartbeat: (networkInterfaceState):"
      //              + networkInterfaceState.getNetworkInterface().getInterfaceName());

      if (networkInterfaceState.getIpAddressStates() != null
          && !networkInterfaceState.getIpAddressStates().isEmpty()) {
        for (IPAddressState addressState : networkInterfaceState.getIpAddressStates()) {
          //          (
          //              "received heartbeat: (networkInterfaceState/ipAddress):"
          //                  + addressState.getIpAddress());
        }
      }
    }
  }

  //  @Override
  //  protected boolean isRetryable(Throwable thrown) {
  //    return false;
  //  }
  //
  //  @Override
  //  public Void call() throws Exception {
  //    ("processing heartbeat:" + heartbeatMessage);
  //
  //    ("received heartbeat: (identifier):" + heartbeatMessage.getSender().getId());
  //        "received heartbeat: (IP):"
  //            + heartbeatMessage
  //                .getSender()
  //                .getClientIPAddressHistoryList()
  //                .get(heartbeatMessage.getSender().getClientIPAddressHistoryList().size() - 1)
  //                .getIpAddress());
  //    ("received heartbeat: (date sent):" + heartbeatMessage.getDateSent());
  //
  //    ("received heartbeat: (battery status):" + heartbeatMessage.getBatteryStatus());
  //
  //        "received heartbeat: (dig fqdn):"
  //            + heartbeatMessage.getDigRequest().getNetworkDiagnosticTest().getFqdn());
  //
  //    doDig(heartbeatMessage);
  //    doService(heartbeatMessage);
  //    doInterface(heartbeatMessage);
  //
  //    return null;
  //  }
}
