package com.walterjwhite.browser.modules.web.concept.api.service;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.modules.web.concept.api.model.RunningApplication;
import com.walterjwhite.browser.modules.web.concept.api.model.Workspace;

public interface WorkspaceCitrixDriver {
  void moveWindowToWorkspace(
      final AuthenticatedWebSession authenticatedWebSession,
      final RunningApplication runningApplication,
      final Workspace workspace);
}
