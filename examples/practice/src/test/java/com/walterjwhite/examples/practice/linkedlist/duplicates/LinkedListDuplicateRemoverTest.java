package com.walterjwhite.examples.practice.linkedlist.duplicates;

import com.walterjwhite.examples.practice.problems.organize.linkedlist.duplicates.DuplicateRemover;
import com.walterjwhite.examples.practice.problems.organize.linkedlist.duplicates.IteratingLinkedListDuplicateRemover;
import java.util.LinkedList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class LinkedListDuplicateRemoverTest {
  //    protected final DuplicateRemover duplicateRemover = new
  // BufferedLinkedListDuplicateRemover();
  protected final DuplicateRemover duplicateRemover = new IteratingLinkedListDuplicateRemover();

  @Test
  void testNull() {
    final LinkedList<Integer> input = null;
    duplicateRemover.removeDuplicates(input);

    Assertions.assertTrue(input == null);

    LOGGER.debug("result: {}", input);
  }

  @Test
  void testSingleElement() {
    final LinkedList<Integer> input = new LinkedList<>();
    input.add(1);

    duplicateRemover.removeDuplicates(input);

    Assertions.assertNotNull(input);
    Assertions.assertTrue(input.size() == 1);

    LOGGER.debug("result: {}", input);
  }

  @Test
  void test2Elements() {
    final LinkedList<Integer> input = new LinkedList<>();
    input.add(1);
    input.add(1);

    duplicateRemover.removeDuplicates(input);

    Assertions.assertNotNull(input);
    Assertions.assertTrue(input.size() == 1);

    LOGGER.debug("result: {}", input);
  }

  @Test
  void test3Elements() {
    final LinkedList<Integer> input = new LinkedList<>();
    input.add(1);
    input.add(1);
    input.add(2);

    duplicateRemover.removeDuplicates(input);

    Assertions.assertNotNull(input);
    Assertions.assertTrue(input.size() == 2);

    LOGGER.debug("result: {}", input);
  }

  @Test
  void testManyElements() {
    final LinkedList<Integer> input = new LinkedList<>();
    input.add(1);
    input.add(1);
    input.add(2);
    input.add(5);
    input.add(6);
    input.add(7);
    input.add(9);
    input.add(10);
    input.add(7);
    input.add(3);

    duplicateRemover.removeDuplicates(input);

    Assertions.assertNotNull(input);
    Assertions.assertTrue(input.size() == 8);

    LOGGER.debug("result: {}", input);
  }
}
