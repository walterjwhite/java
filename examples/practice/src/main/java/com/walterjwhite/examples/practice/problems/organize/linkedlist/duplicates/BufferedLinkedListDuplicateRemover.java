package com.walterjwhite.examples.practice.problems.organize.linkedlist.duplicates;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BufferedLinkedListDuplicateRemover implements DuplicateRemover {
  public void removeDuplicates(final LinkedList<Integer> input) {
    if (input == null) {
      return;
    }

    if (input.size() == 1) {
      return;
    }

    final Set<Integer> keys = new HashSet<>();
    for (int i = 0; i < input.size(); i++) {
      final Integer value = input.get(i);
      if (keys.contains(value)) {
        input.remove(i);
      }

      keys.add(value);
    }
  }
}
