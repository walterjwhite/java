package com.walterjwhite.examples.practice.problems.organize.fibonacci;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// up to 100
public class Fibonacci {
//  protected final Sequence start = new Sequence(1, new Sequence(0, null));
//  protected final int maxValue = 100;
//
  public void print() {

//    Sequence next = start;
//    while (next != null) {
//      next = next.getNext();
//    }
  }
//
//  protected Sequence next(Sequence current) {
//    current.setNext(new Sequence(current.getPrevious().getValue() + current.getValue(), current));
//
//    if (current.getNext().getValue() < maxValue) {
//      return next(current.getNext());
//    }
//
//    return current.getNext();
//  }

  @Setter
  @Getter
  @RequiredArgsConstructor
  private static class Sequence {}
}
