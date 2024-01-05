package com.walterjwhite.examples.practice.problems.stacks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Node<Type> {
  protected final Type value;
  @Setter protected Node<Type> next;
}
