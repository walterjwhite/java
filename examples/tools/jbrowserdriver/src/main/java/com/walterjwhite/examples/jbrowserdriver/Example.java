package com.walterjwhite.examples.jbrowserdriver;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;

public class Example {
  public static void main(String[] args) {

    JBrowserDriver driver =
        new JBrowserDriver(Settings.builder().timezone(Timezone.AMERICA_NEWYORK).build());

    driver.get("http://example.com");



    driver.quit();
  }
}
