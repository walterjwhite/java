package com.walterjwhite.browser.api.user;

import com.walterjwhite.delay.RandomDelayable;
import java.time.Duration;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class BrowserRandomDelayable implements RandomDelayable {
  protected Duration minimum;
  protected Duration maximum;
}
