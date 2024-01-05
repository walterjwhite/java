package com.walterjwhite.browser.modules.web.concept.citrix.service;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.modules.web.concept.api.model.RunningApplication;
import com.walterjwhite.browser.modules.web.concept.api.service.DesktopDriver;

public class CitrixDesktopDriver implements DesktopDriver<AuthenticatedWebSession> {
  @Override
  public RunningApplication getFocusedWindow(AuthenticatedWebSession session) {
    return null;
  }

  @Override
  public void switchTo(AuthenticatedWebSession session, RunningApplication runningApplication) {
    // Alt-tab
    // switch workspace
  }

  @Override
  public void close(AuthenticatedWebSession session, RunningApplication runningApplication) {
    switchTo(session, runningApplication);

    // find and click the 'close' icon
  }

  @Override
  public void minimize(AuthenticatedWebSession session, RunningApplication runningApplication) {
    switchTo(session, runningApplication);

    // find and click the 'minimize' icon
  }

  @Override
  public void maximize(AuthenticatedWebSession session, RunningApplication runningApplication) {
    switchTo(session, runningApplication);

    // find and click the 'maximize' icon
  }

  @Override
  public void fullScreen(AuthenticatedWebSession session, RunningApplication runningApplication) {
    switchTo(session, runningApplication);

    // some applications support full-screen
    // send F11
  }

  @Override
  public byte[] takeScreenshot(
      AuthenticatedWebSession session, AuthenticatedWebSession authenticatedWebSession) {
    return new byte[0];
  }
}
