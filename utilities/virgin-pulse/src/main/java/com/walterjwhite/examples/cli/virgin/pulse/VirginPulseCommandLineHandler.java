package com.walterjwhite.examples.cli.virgin.pulse;

import com.walterjwhite.browser.plugins.virgin.pulse.api.model.VirginPulseWebSession;
import com.walterjwhite.browser.plugins.virgin.pulse.api.service.VirginPulseWebsitePlugin;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class VirginPulseCommandLineHandler implements CommandLineHandler {
  protected final VirginPulseWebsitePlugin virginPulseWebsitePlugin;
  protected final VirginPulseWebSession virginPulseWebSession;

  @Inject
  public VirginPulseCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      VirginPulseWebsitePlugin virginPulseWebsitePlugin,
      final VirginPulseWebSession virginPulseWebSession) {
    //    super(shutdownTimeoutInSeconds);
    this.virginPulseWebsitePlugin = virginPulseWebsitePlugin;
    this.virginPulseWebSession = virginPulseWebSession;
  }

  @Override
  public void run(String... arguments) throws Exception {
    virginPulseWebsitePlugin.execute(virginPulseWebSession);
  }
}
