package com.walterjwhite.examples.practice.problems.organize.recursion;

import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class Node {
  protected final int i;
  protected final int j;

  protected final Map<Direction, Node> connectedNodes = new HashMap<>();
}
