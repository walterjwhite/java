package com.walterjwhite.print.providers.cups4j;

import com.walterjwhite.print.providers.cups4j.property.CupsPassword;
import com.walterjwhite.print.providers.cups4j.property.CupsPort;
import com.walterjwhite.print.providers.cups4j.property.CupsServer;
import com.walterjwhite.print.providers.cups4j.property.CupsUser;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.cups4j.CupsClient;

public class CupsClientProvider implements Provider<CupsClient> {
  protected final CupsClient cupsClient;

  @Inject
  public CupsClientProvider(
      @Property(CupsPort.class) int cupsPort,
      @Property(CupsServer.class) String cupsServer,
      @Property(CupsUser.class) String cupsUser,
      @Property(CupsPassword.class) String cupsPassword)
      throws Exception {
    if (cupsUser != null) this.cupsClient = new CupsClient(cupsServer, cupsPort, cupsUser);
    else this.cupsClient = new CupsClient(cupsServer, cupsPort);
  }

  @Override
  public CupsClient get() {
    return cupsClient;
  }
}
