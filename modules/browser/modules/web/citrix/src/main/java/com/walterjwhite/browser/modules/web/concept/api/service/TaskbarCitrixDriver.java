package com.walterjwhite.browser.modules.web.concept.api.service;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.modules.web.concept.api.model.Notification;
import com.walterjwhite.browser.modules.web.concept.api.model.RunningApplication;
import com.walterjwhite.browser.modules.web.concept.api.model.TaskbarIcon;

public interface TaskbarCitrixDriver {
  TaskbarIcon[] getRunningApplications(final AuthenticatedWebSession authenticatedWebSession);

  RunningApplication[] getFlashingRunningApplications(
      final AuthenticatedWebSession authenticatedWebSession);

  Notification[] getNotifications(final AuthenticatedWebSession authenticatedWebSession);
}
