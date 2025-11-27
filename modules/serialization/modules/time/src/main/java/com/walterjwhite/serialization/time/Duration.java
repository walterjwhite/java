package com.walterjwhite.serialization.time;

import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Duration {
  protected long amount;
  protected ChronoUnit unit;

  public java.time.Duration toJavaDuration() {
    return java.time.Duration.of(amount, unit);
  }
}
