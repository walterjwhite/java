package com.walterjwhite.examples.jbrowserdriver;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;

public class Example {
  public static void main(String[] args) {

    // You can optionally pass a Settings object here,
    // constructed using Settings.Builder
    JBrowserDriver driver =
        new JBrowserDriver(Settings.builder().timezone(Timezone.AMERICA_NEWYORK).build());

    // This will block for the page load and any
    // associated AJAX requests
    driver.get("http://example.com");

    // You can get status code unlike other Selenium drivers.
    // It blocks for AJAX requests and page loads after clicks
    // and keyboard events.

    // Returns the page source in its current state, including
    // any DOM updates that occurred after page load

    // Close the browser. Allows this thread to terminate.
    driver.quit();
  }
}
