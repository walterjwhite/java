package com.walterjwhite.examples.practice.problems.organize.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class SinglyLinkedList {
  protected int value;
  // @ToString.Exclude
  protected SinglyLinkedList next;

  public SinglyLinkedList(int value) {
    this.value = value;
  }

  public int count() {
    if (next != null) {
      return 1 + next.count();
    }

    return 1;
  }
}
