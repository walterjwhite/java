package com.walterjwhite.examples.practice.problems.organize.strings;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UniqueCharactersTest {
  @Test
  void runTests() {
    Arrays.stream(TestCase.values()).forEach(testCase -> testCase.test());
  }

  @Getter
  @RequiredArgsConstructor
  enum TestCase {
    Null {
      @Override
      protected String getInput() {
        return null;
      }
    },
    Single,
    Aa,
    aa(false),
    Abcdefghijklmnopqrss(false);

    final boolean expectedResult;

    TestCase() {
      expectedResult = true;
    }

    public void test() {
      final String input = getInput();
      Assertions.assertEquals(
          expectedResult,
          ContainsAllUniqueCharacters.containsAllUniqueCharacters(input),
          String.format("input: %s", input));
    }

    protected String getInput() {
      return name();
    }
  }
}
