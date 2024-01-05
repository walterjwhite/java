package com.walterjwhite.browser.modules.ashot.renderer;

import com.walterjwhite.browser.modules.ashot.renderer.property.AshotScrollTimeout;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class AShotProvider implements Provider<AShot> {
  protected final int scrollTimeout;

  @Inject
  public AShotProvider(@Property(AshotScrollTimeout.class) int scrollTimeout) {
    this.scrollTimeout = scrollTimeout;
  }

  @Override
  public AShot get() {
    // each browser service should have a single instance
    final AShot ashot = new AShot();
    ashot.shootingStrategy(ShootingStrategies.viewportPasting(scrollTimeout));

    return (ashot);
  }
}
