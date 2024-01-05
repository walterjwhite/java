package com.walterjwhite.browser.modules.ashot.renderer;

import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.browser.api.util.WebDriverUtil;
import com.walterjwhite.browser.modules.ashot.renderer.property.AshotScreenshotOutputFormat;
import com.walterjwhite.property.api.annotation.Property;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

@Slf4j
public class AShotPageRendererService {
  protected final AShot ashot;

  protected final AshotScreenshotOutputFormat ashotScreenshotOutputFormat;

  @Inject
  public AShotPageRendererService(
      AShot ashot,
      @Property(AshotScreenshotOutputFormat.class)
          final AshotScreenshotOutputFormat ashotScreenshotOutputFormat) {

    this.ashot = ashot;
    this.ashotScreenshotOutputFormat = ashotScreenshotOutputFormat;
  }

  public void save(WebSession webSession, final OutputStream outputStream) throws IOException {
    try {
      final WebDriver augmentedWebDriver = new Augmenter().augment(webSession.getWebDriver());
      Screenshot screenshot = ashot.takeScreenshot(augmentedWebDriver);
      ImageIO.write(
          screenshot.getImage(), ashotScreenshotOutputFormat.getExtension(), outputStream);
    } catch (Exception e) {
      WebDriverUtil.logException(webSession, e);
      throw e;
    } finally {
      outputStream.close();
    }
  }
}
