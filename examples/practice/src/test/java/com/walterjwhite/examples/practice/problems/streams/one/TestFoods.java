package com.walterjwhite.examples.practice.problems.streams.one;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TestFoods {
  Pizza(new Food("pizza", 2000)),
  Tacos(new Food("taco", 1200)),
  Chips(new Food("chips", 120)),
  ChocolateChipCookie(new Food("chocolate chip cookie", 200)),
  BananaBread(new Food("banana bread", 202));

  private final Food food;
}
