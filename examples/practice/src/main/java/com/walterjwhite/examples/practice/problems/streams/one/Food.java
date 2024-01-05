package com.walterjwhite.examples.practice.problems.streams.one;

import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public final class Food {
  protected final String name;
  @ToString.Exclude
  protected final int calories;
}
