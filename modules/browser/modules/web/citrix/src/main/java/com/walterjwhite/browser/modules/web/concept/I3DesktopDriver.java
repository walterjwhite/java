package com.walterjwhite.browser.modules.web.concept;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.modules.web.concept.api.model.RunningApplication;
import com.walterjwhite.browser.modules.web.concept.api.service.DesktopDriver;

public class I3DesktopDriver implements DesktopDriver<Void> {
  @Override
  public RunningApplication getFocusedWindow(Void session) {
    return null;
  }

  @Override
  public void switchTo(Void session, RunningApplication runningApplication) {}

  @Override
  public void close(Void session, RunningApplication runningApplication) {
    // send close hotkey (CTRL+Q)
  }

  @Override
  public void minimize(Void session, RunningApplication runningApplication) {
    // not implemented
  }

  @Override
  public void maximize(Void session, RunningApplication runningApplication) {
    // not implemented
  }

  @Override
  public void fullScreen(Void session, RunningApplication runningApplication) {
    // send close hotkey (WINDOWS+F)
  }

  @Override
  public byte[] takeScreenshot(Void session, AuthenticatedWebSession authenticatedWebSession) {
    return new byte[0];
  }
}
