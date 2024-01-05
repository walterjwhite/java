package com.walterjwhite.examples.practice.problems.stacks.set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Node {
  protected final Integer value;
  @Setter protected Node next;
}
