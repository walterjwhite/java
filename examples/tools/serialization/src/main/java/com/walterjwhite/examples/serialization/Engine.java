package com.walterjwhite.examples.serialization;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
public class Engine implements Serializable {
  protected String manufacturer;
  protected long displacement;
  protected long maxRpms;
  protected long minRpms;
}
