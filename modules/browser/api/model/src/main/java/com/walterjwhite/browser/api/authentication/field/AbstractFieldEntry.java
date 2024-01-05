package com.walterjwhite.browser.api.authentication.field;

import com.walterjwhite.browser.api.model.WebSession;
import lombok.Data;
import lombok.ToString;
import org.openqa.selenium.WebElement;

@ToString(doNotUseGetters = true)
@Data
public abstract class AbstractFieldEntry {
  protected String fieldPairId;

  public abstract WebElement enter(final WebSession webSession, final FieldPair fieldPair)
      throws InterruptedException;
}
