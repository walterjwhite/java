package com.walterjwhite.examples.sikulix;

import java.net.URL;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sikuli.script.Image;
import org.sikuli.script.Screen;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SikulixUtil {
  public static Image getClasspathImage(final String relativeImagePath) {
    final URL url = SikulixUtil.class.getResource(relativeImagePath);
    LOGGER.debug("url: {} | {}", url, relativeImagePath);
    return Image.create(url);
  }

  public static Screen getScreen(final Image target) {
    for (int i = 0; i < Screen.getNumberScreens(); i++) {
      final Screen screen = new Screen(i);

      try {
        screen.click(target);
        return screen;
      } catch (Exception e) {
        LOGGER.warn("{} not found on screen: {}", target, screen);
      }
    }

    throw new RuntimeException(
        String.format("Target not found: %s | %d", target, Screen.getNumberScreens()));
  }
}
