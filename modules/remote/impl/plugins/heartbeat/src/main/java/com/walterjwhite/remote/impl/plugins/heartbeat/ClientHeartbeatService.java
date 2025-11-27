package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.shell.api.enumeration.ServiceAction;
import com.walterjwhite.shell.api.model.*;
import com.walterjwhite.shell.api.model.dig.DigRequest;
import com.walterjwhite.shell.api.model.ping.PingRequest;
import com.walterjwhite.shell.api.model.traceroute.TracerouteRequest;
import com.walterjwhite.shell.api.service.*;
import com.walterjwhite.shell.api.service.unused.NetworkInterfaceService;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Singleton
public class ClientHeartbeatService extends AbstractScheduledService {
  protected final MessageWriterService messageWriterService;

  protected final Provider<DigService> digServiceProvider;
  protected final Provider<TracerouteService> tracerouteServiceProvider;
  protected final Provider<PingService> pingServiceProvider;

  protected final Provider<NetworkInterfaceService> networkInterfaceServiceProvider;

  protected final Provider<SystemServiceService> systemServiceServiceProvider;

  protected final Provider<UpowerService> upowerServiceProvider;


  protected final int heartbeatMaximumInterval;

  protected LocalDateTime nextRuntime = LocalDateTime.now();

  @Inject
  public ClientHeartbeatService(
      MessageWriterService messageWriterService,
      Provider<DigService> digServiceProvider,
      Provider<TracerouteService> tracerouteServiceProvider,
      Provider<PingService> pingServiceProvider,
      Provider<NetworkInterfaceService> networkInterfaceServiceProvider,
      Provider<SystemServiceService> systemServiceServiceProvider,
      Provider<UpowerService> upowerServiceProvider,
      @Property(HeartbeatMaximumInterval.class) int heartbeatMaximumInterval) {

    this.digServiceProvider = digServiceProvider;
    this.messageWriterService = messageWriterService;
    this.tracerouteServiceProvider = tracerouteServiceProvider;
    this.pingServiceProvider = pingServiceProvider;
    this.networkInterfaceServiceProvider = networkInterfaceServiceProvider;
    this.systemServiceServiceProvider = systemServiceServiceProvider;
    this.upowerServiceProvider = upowerServiceProvider;

    this.heartbeatMaximumInterval = heartbeatMaximumInterval;
  }

  public void doSendHeartbeat() throws Exception {
    final List<Service> services = null;

    final HeartbeatMessage heartbeatMessage;

    final Set<ServiceStatus> serviceStatuses = new HashSet<>();
    for (final Service service : services) {
      serviceStatuses.add(
          systemServiceServiceProvider
              .get()
              .execute(
                  new ServiceCommand().withService(service).withServiceAction(ServiceAction.Status))
              .getServiceStatus());
    }

    final NetworkDiagnosticTest networkDiagnosticTest = null;
    DigRequest digRequest = digServiceProvider.get().execute(new DigRequest(networkDiagnosticTest));

    TracerouteRequest tracerouteRequest =
        tracerouteServiceProvider.get().execute(new TracerouteRequest(networkDiagnosticTest));

    PingRequest pingRequest =
        pingServiceProvider.get().execute(new PingRequest(networkDiagnosticTest));

    heartbeatMessage = new HeartbeatMessage();
    heartbeatMessage.setTimeToLive(3600000);
    heartbeatMessage.getServiceStatuses().addAll(serviceStatuses);
    heartbeatMessage.setDigRequest(digRequest);
    heartbeatMessage.setTracerouteRequest(tracerouteRequest);
    heartbeatMessage.setPingRequest(pingRequest);
    heartbeatMessage
        .getNetworkInterfaceStates()
        .addAll(networkInterfaceServiceProvider.get().getNetworkInterfaceStatesForNode());
    heartbeatMessage.setBatteryStatus(
        upowerServiceProvider.get().execute(new BatteryRequest()).getBatteryStatus());

    try {
      messageWriterService.write(heartbeatMessage);

    } catch (Exception e) {
      throw new RuntimeException("Error writing message", e);
    }
  }

  @Override
  protected void runOneIteration() throws Exception {
    if (isRun()) {
      doSendHeartbeat();
      scheduleNextRun();
    }
  }

  protected boolean isRun() {
    return (LocalDateTime.now().isAfter(nextRuntime));
  }

  protected LocalDateTime scheduleNextRun() {
    nextRuntime = LocalDateTime.now().plusMinutes(getDelayInMinutes());
    return nextRuntime;
  }

  protected int getDelayInMinutes() {
    Random generator = new Random();
    return generator.nextInt(heartbeatMaximumInterval) + 1;
  }

  @Override
  protected Scheduler scheduler() {
    return AbstractScheduledService.Scheduler.newFixedDelaySchedule(0, 1, TimeUnit.MINUTES);
  }
}
