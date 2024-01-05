package com.walterjwhite.browser.modules.web.concept.api.service;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.modules.web.concept.api.model.RunningApplication;

public interface DesktopDriver<SessionType> {
  RunningApplication getFocusedWindow(final SessionType session);

  void switchTo(final SessionType session, final RunningApplication runningApplication);

  void close(final SessionType session, final RunningApplication runningApplication);

  void minimize(final SessionType session, final RunningApplication runningApplication);

  void maximize(final SessionType session, final RunningApplication runningApplication);

  void fullScreen(final SessionType session, final RunningApplication runningApplication);

  /*Screenshot*/ byte[] takeScreenshot(
      final SessionType session, final AuthenticatedWebSession authenticatedWebSession);
}
