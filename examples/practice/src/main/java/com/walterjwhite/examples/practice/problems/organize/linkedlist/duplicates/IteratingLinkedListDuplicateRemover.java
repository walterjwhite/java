package com.walterjwhite.examples.practice.problems.organize.linkedlist.duplicates;

import java.util.LinkedList;

public class IteratingLinkedListDuplicateRemover implements DuplicateRemover {
  @Override
  public void removeDuplicates(LinkedList<Integer> input) {
    if (input == null) {
      return;
    }

    if (input.size() == 1) {
      return;
    }

    for (int i = 0; i < input.size(); i++) {
      final Integer value = input.get(i);
      removeDuplicates(input, value, i + 1);
    }
  }

  protected void removeDuplicates(final LinkedList<Integer> input, final int key, final int start) {
    for (int i = start; i < input.size(); i++) {
      final Integer value = input.get(i);
      if (value == key) {
        input.remove(i);
      }
    }
  }
}
