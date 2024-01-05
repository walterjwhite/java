package com.walterjwhite.modules.shell.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.shell.api.model.Node;
import com.walterjwhite.shell.api.service.*;
import com.walterjwhite.shell.impl.provider.NodeProvider;
import com.walterjwhite.shell.impl.service.*;

class ShellModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(ShellCommandBuilder.class);

    bind(DigService.class).to(DefaultDigService.class);
    bind(TracerouteService.class).to(DefaultTracerouteService.class);
    //
    // bind(NetworkManagerManagedNetworkInterfacesService.class).to(DefaultNetworkManagerManagedNetworkInterfacesService.class);
    bind(ShellExecutionService.class).to(DefaultShellExecution.class);
    bind(UpowerService.class).to(DefaultUpowerService.class);
    bind(SystemServiceService.class).to(UnixSystemServiceService.class);

    bind(PingService.class).to(DefaultPingService.class);
    // only used in the heartbeat service
    // bind(NetworkInterfaceService.class).to(/*DefaultNetworkInterfaceService*/DefaultNetworkManagerManagedNetworkInterfacesService.class);
    bind(Node.class).toProvider(NodeProvider.class); // .in(Singleton.class);

    // register the interceptor to create commands
    //    bindInterceptor(
    //        any(),
    //        annotatedWith(EntityEnabled.class),
    //        new EntityInterceptor(getProvider(Repository.class)));
  }
}
