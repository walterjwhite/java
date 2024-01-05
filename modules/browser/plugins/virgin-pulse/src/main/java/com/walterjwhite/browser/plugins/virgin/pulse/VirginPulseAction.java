package com.walterjwhite.browser.plugins.virgin.pulse;

import com.walterjwhite.browser.plugins.virgin.pulse.api.model.VirginPulseWebSession;

public interface VirginPulseAction {
  void execute(final VirginPulseWebSession virginPulseWebSession, final String argument)
      throws Exception;
}
