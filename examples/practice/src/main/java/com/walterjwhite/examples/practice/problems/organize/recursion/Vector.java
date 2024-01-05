package com.walterjwhite.examples.practice.problems.organize.recursion;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Vector {
  protected final int x;
  protected final int y;
  protected final Direction direction;
}
