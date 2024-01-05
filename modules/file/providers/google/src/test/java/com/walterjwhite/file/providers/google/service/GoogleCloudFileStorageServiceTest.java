package com.walterjwhite.file.providers.google.service;

import com.walterjwhite.google.guice.GuiceHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GoogleCloudFileStorageServiceTest {
  @Before
  public void onBefore() {
    GuiceHelper.addModules(new GoogleCloudFileStorageTestModule(getClass()));
    GuiceHelper.setup();
  }

  @After
  public void onAfter() {
    GuiceHelper.stop();
  }

  @Test
  public void testBasics() {
    /*
        GoogleCloudFileStorageService googleCloudFileStorageService =
            GuiceHelper.getGuiceApplicationInjector().getInstance(GoogleCloudFileStorageService.class);
        final File file = new File("/tmp/test");

        googleCloudFileStorageService.put(file);
        googleCloudFileStorageService.get(file);

        LOGGER.info("source:" + file.getSource());

        googleCloudFileStorageService.delete(file);
        LOGGER.info("deleted file");
    */
  }
}
