package com.walterjwhite.examples.practice.problems.streams.one;

import static java.util.stream.Collectors.toUnmodifiableList;
//import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("SimplifyStreamApiCallChains")
@DisplayName("Stream - Java 11")
public class StreamTest01 {
  private static final Set<Food> ALL_FOODS =
      Set.of(
          TestFoods.Pizza.getFood(),
          TestFoods.Tacos.getFood(),
          TestFoods.Chips.getFood(),
          TestFoods.ChocolateChipCookie.getFood(),
          TestFoods.BananaBread.getFood());

  @Test
  @DisplayName("Task: Get all Foods except banana bread")
  void task1() {
    final List<Food> everythingExceptBanana =
        ALL_FOODS.stream()
            .filter(food -> !TestFoods.BananaBread.getFood().equals(food.getName()))
            .collect(toUnmodifiableList());
//    Assertions.assertTrue(
//        everythingExceptBanana.stream()
//            .col(TestFoods.BananaBread)
//            .containsExactlyInAnyOrder(
//                TestFoods.Pizza.getFood(),
//                TestFoods.Tacos.getFood(),
//                TestFoods.Chips.getFood(),
//                TestFoods.ChocolateChipCookie.getFood()));
  }

  @Test
  @DisplayName("Task: Load Food list from file")
  void task2() throws IOException, URISyntaxException {
    // Loads the String from resources file
    URI resource = ClassLoader.getSystemResource("java11/FoodList.txt").toURI();
    final List<Food> actualFoodList = null;
//    assertThat(actualFoodList).containsExactlyInAnyOrderElementsOf(ALL_FOODS);
  }
}
