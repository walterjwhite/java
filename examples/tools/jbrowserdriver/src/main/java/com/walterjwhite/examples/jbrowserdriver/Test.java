package com.walterjwhite.examples.jbrowserdriver;

import com.machinepublishers.jbrowserdriver.*;
import java.io.File;

public class Test {

  public static void main(final String[] arguments) {
    try {
      builder();
    } catch (Exception e) {
      handleError(e);
    }
  }

  private static void handleError(Exception e) {}

  private static void builder() {
    Settings.Builder builder = Settings.builder();

    builder.javaOptions(
        "-classpath",
        "~/.m2/repository/com/machinepublishers/jbrowserdriver/1.0.0-RC1/jbrowserdriver-1.0.0-RC1-uberjar.jar");
    builder.timezone(Timezone.AMERICA_NEWYORK);
    builder.ssl("trustanything");

    builder.ajaxResourceTimeout(5);
    builder.ajaxWait(5);
    builder.userAgent(UserAgent.CHROME);

    builder.connectTimeout(5);

    builder.saveAttachments(true);

    builder.cacheDir(new File("/tmp/jbrowserdriver"));

    builder.proxy(new ProxyConfig(ProxyConfig.Type.HTTP, "localhost", 8118));

    JBrowserDriver jBrowserDriver = new JBrowserDriver(builder.build());

    jBrowserDriver.get("google.com");
    jBrowserDriver.close();
  }

  private static void basic() {

    JBrowserDriver driver =
        new JBrowserDriver(
            Settings.builder()
                .timezone(Timezone.AMERICA_NEWYORK)
                .proxy(new ProxyConfig(ProxyConfig.Type.HTTP, "localhost", 8118))
                .build());

    driver.get("http://lxer.com");



    driver.quit();
  }
}
