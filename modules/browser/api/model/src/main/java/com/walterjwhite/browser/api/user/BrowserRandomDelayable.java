package com.walterjwhite.browser.api.user;

import com.walterjwhite.delay.RandomDelayable;
import java.time.Duration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class BrowserRandomDelayable implements RandomDelayable {
  protected Duration minimum;
  protected Duration maximum;
}
