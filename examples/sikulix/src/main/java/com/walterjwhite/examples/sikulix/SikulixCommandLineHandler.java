package com.walterjwhite.examples.sikulix;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sikuli.script.Image;
import org.sikuli.script.Screen;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class SikulixCommandLineHandler implements CommandLineHandler {
  @Override
  public void run(String... arguments) {
    LOGGER.info("running sikulix");

    final Image vscodeIcon = SikulixUtil.getClasspathImage("sikulix/vscode-icon.png");
    final Screen screen = SikulixUtil.getScreen(vscodeIcon);

    LOGGER.info("screen: {}", screen);
  }
}
