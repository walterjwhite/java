package com.walterjwhite.browser.chromedriver;

import com.walterjwhite.browser.api.model.ResourceURI;
import com.walterjwhite.browser.api.service.BrowserService;
import com.walterjwhite.google.guice.GuiceHelper;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBrowserUserAgent {
  @Before
  public void onBefore() throws Exception {
    //    GuiceHelper.addModules(
    //        new GuiceTestModule(
    //            new Reflections(
    //                "com.walterjwhite",
    //                TypeAnnotationsScanner.class,
    //                SubTypesScanner.class,
    //                FieldAnnotationsScanner.class),
    //            new PropertyValuePair(KeyFilePath.class),
    //            new PropertyValuePair(IVFilePath.class),
    //        new FileStorageModule(),
    //        new DefaultFileStorageModule(),
    //        new CompressionModule(),
    //        new EncryptionModule(),
    //        new GoogleGuicePersistModule(),
    //        new JpaPersistModule("defaultJPAUnit"),
    //        new CriteriaBuilderModule(),
    //        new JBrowserDriverModule(),
    //        new BrowserServiceModule());
    GuiceHelper.setup();
  }

  @After
  public void onAfter() {
    GuiceHelper.stop();
  }

  @Test
  public void testPost() throws IOException {
    BrowserService browserService =
        GuiceHelper.getGuiceApplicationInjector().getInstance(BrowserService.class);
    // browserService.executeScript(String.format("navigator.sayswho= (function(){\nvar ua=
    // navigator.userAgent, tem,\nnullif(/trident/i.test(M[1])){\nnullreturn 'IE '+(tem[1] ||
    // '');\n}\nif(M[1]=== 'Chrome'){\nnullif(tem!= null) return tem.slice(1).join('
    // ').replace('OPR', 'Opera');\n}\nM= M[2]? [M[1], M[2]]: [navigator.appName,
    // navigator.appVersion, '-?'];\nnullreturn M.join('
    // ');\n})();\nconsole.log(navigator.sayswho);"));
    // browserService.get(new
    // ResourceURI("https://www.whatismybrowser.com/detect/what-is-my-user-agent"));
    browserService.get(
        new ResourceURI("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_nav_all"));
    browserService.get(new ResourceURI("https://www.quirksmode.org/js/detect.html"));
  }
}
