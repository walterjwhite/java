package com.walterjwhite.examples.practice.problems.streams.one;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Stream - Java 11")
public class StreamTest02 {
  public static final Set<Food> FOODS =
      Set.of(
          TestFoods.Pizza.getFood(),
          TestFoods.Tacos.getFood(),
          TestFoods.Chips.getFood(),
          TestFoods.ChocolateChipCookie.getFood(),
          TestFoods.BananaBread.getFood());

  @Test
  @DisplayName("Task: Find 2 foods with biggest amount of calories")
  void task1() {
    final List<Food> mostCaloricFoods =
        FOODS.parallelStream()
            .sorted(Comparator.comparingInt(Food::getCalories).reversed())
            .limit(2)
            .collect(Collectors.toList());

    Assertions.assertTrue(
        mostCaloricFoods.contains(TestFoods.Pizza.getFood())
            && mostCaloricFoods.contains(TestFoods.Tacos.getFood())
            && mostCaloricFoods.size() == 2);
  }

  @Test
  @DisplayName("Task: Take half of each food and get the sum of calories")
  void task2() {
    final int sumOfCalories =
        FOODS.parallelStream().mapToInt((food) -> food.getCalories() / 2).sum();
    Assertions.assertEquals(1861, sumOfCalories);
  }

  @Test
  @DisplayName("Task: Group foods by first letter")
  void task3() {
    final Map<Character, Set<Food>> mapOfFoods =
        FOODS.stream()
            .collect(
                Collectors.groupingBy(
                    food -> food.getName().charAt(0),
                    Collectors.mapping(food -> food, Collectors.toSet())));

    Assertions.assertTrue(mapOfFoods.keySet().contains(Character.valueOf('p')));
    Assertions.assertTrue(mapOfFoods.keySet().contains(Character.valueOf('t')));
    Assertions.assertTrue(mapOfFoods.keySet().contains(Character.valueOf('c')));
    Assertions.assertTrue(mapOfFoods.keySet().contains(Character.valueOf('b')));

    Assertions.assertTrue(mapOfFoods.get(Character.valueOf('p')).size() == 1);
    Assertions.assertTrue(mapOfFoods.get(Character.valueOf('t')).size() == 1);
    Assertions.assertTrue(mapOfFoods.get(Character.valueOf('c')).size() == 2);
    Assertions.assertTrue(mapOfFoods.get(Character.valueOf('b')).size() == 1);
  }

  @Test
  @DisplayName("Task: Put all foods into one basket")
  void task4() {
    final List<List<Food>> foodBaskets =
        List.of(
            List.of(TestFoods.Pizza.getFood(), TestFoods.Tacos.getFood()),
            List.of(TestFoods.Chips.getFood(), TestFoods.ChocolateChipCookie.getFood()));

    final List<Food> basketWithAllFoods =
        foodBaskets.stream().flatMap(basket -> basket.stream()).collect(Collectors.toList());

    Assertions.assertTrue(basketWithAllFoods.contains(TestFoods.Pizza.getFood()));
    Assertions.assertTrue(basketWithAllFoods.contains(TestFoods.Tacos.getFood()));
    Assertions.assertTrue(basketWithAllFoods.contains(TestFoods.Chips.getFood()));
    Assertions.assertTrue(basketWithAllFoods.contains(TestFoods.ChocolateChipCookie.getFood()));

    Assertions.assertFalse(basketWithAllFoods.contains(TestFoods.BananaBread.getFood()));
  }

  @Test
  @DisplayName("Task: Count amount of each food in the basket")
  void task5() {
    final List<Food> basket =
        List.of(
            TestFoods.Pizza.getFood(),
            TestFoods.Tacos.getFood(),
            TestFoods.Chips.getFood(),
            TestFoods.ChocolateChipCookie.getFood(),
            TestFoods.BananaBread.getFood(),
            TestFoods.BananaBread.getFood(),
            TestFoods.BananaBread.getFood());

    final Map<Food, Long> countedFood =
        basket.stream().collect(Collectors.groupingBy(food -> food, Collectors.counting()));

    Assertions.assertTrue(
        countedFood.containsKey(TestFoods.Pizza.getFood())
            && countedFood.get(TestFoods.Pizza.getFood()) == 1);
    Assertions.assertTrue(
        countedFood.containsKey(TestFoods.Tacos.getFood())
            && countedFood.get(TestFoods.Tacos.getFood()) == 1);
    Assertions.assertTrue(
        countedFood.containsKey(TestFoods.Chips.getFood())
            && countedFood.get(TestFoods.Chips.getFood()) == 1);
    Assertions.assertTrue(
        countedFood.containsKey(TestFoods.ChocolateChipCookie.getFood())
            && countedFood.get(TestFoods.ChocolateChipCookie.getFood()) == 1);
    Assertions.assertTrue(
        countedFood.containsKey(TestFoods.BananaBread.getFood())
            && countedFood.get(TestFoods.BananaBread.getFood()) == 3);
  }

  @Test
  @DisplayName("Task: Mix all foods together and construct one, big, new Food")
  void task6() {
    final Food bigJuicyFood =
        new Food(
            FOODS.stream().map(food -> food.getName()).collect(Collectors.joining(" ")),
            FOODS.stream().collect(Collectors.summingInt(Food::getCalories)));

    Assertions.assertNotNull(bigJuicyFood);
    Assertions.assertEquals(3722, bigJuicyFood.getCalories());
    Assertions.assertNotNull(bigJuicyFood.getName());
  }

  @Test
  @DisplayName("Task: Generate list of 10 randomly picked foods")
  void task7() {
    final Supplier<Food> randomFoodSupplier =
        new Supplier<>() {
          final Random random = new Random();
          final List<Food> foods = new ArrayList<>(FOODS);

          @Override
          public Food get() {
            return foods.get(random.nextInt(foods.size()));
          }
        };

    final Stream<Food> infiniteStreamOfFoods = Stream.generate(randomFoodSupplier);

    List<Food> pickedFoods = infiniteStreamOfFoods.limit(10).collect(Collectors.toList());

    Assertions.assertEquals(10, pickedFoods.size());
  }

  @Test
  @DisplayName("Task: Collect only second and forth (with odd index) food from foodList")
  void task8() {
    // Do you know why list have to be sorted?
    final List<Food> foodList =
        new ArrayList<>(FOODS)
            .stream().sorted(Comparator.comparing(Food::getName)).collect(Collectors.toList());

    //        final AtomicInteger index = new AtomicInteger();
    //        final List<Food> filteredFoods = foodList.stream()
    //                .takeWhile(food ->
    //                        index.addAndGet(1) != 1 || index.get() !=
    // 3).collect(Collectors.toList());
    final List<Food> filteredFoods =
        IntStream.range(0, foodList.size())
            .filter(index -> index % 2 == 1)
            .mapToObj(foodList::get)
            .collect(Collectors.toList());

    Assertions.assertEquals(2, filteredFoods.size());
    Assertions.assertTrue(filteredFoods.contains(TestFoods.Chips.getFood()));
    Assertions.assertTrue(filteredFoods.contains(TestFoods.Pizza.getFood()));
  }
}
