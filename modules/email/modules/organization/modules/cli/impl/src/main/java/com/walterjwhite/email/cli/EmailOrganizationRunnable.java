package com.walterjwhite.email.cli;

import com.walterjwhite.email.organization.EmailOrganizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EmailOrganizationRunnable implements Runnable {
  protected final EmailOrganizer emailOrganizer;

  @Override
  public void run() {
    try {
      emailOrganizer.process();
    } catch (IndexOutOfBoundsException e) {
      LOGGER.warn("IndexOutOfBound", e);
    }
  }
}
