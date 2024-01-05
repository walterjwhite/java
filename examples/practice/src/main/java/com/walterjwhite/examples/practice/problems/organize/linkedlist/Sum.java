package com.walterjwhite.examples.practice.problems.organize.linkedlist;

@Deprecated
public class Sum {
  private Sum() {}

  public static SinglyLinkedList sumReverse(SinglyLinkedList a, SinglyLinkedList b) {
    final SinglyLinkedList result = a;

    int remainder = 0;
    while (true) {
      final int sum = a.getValue() + b.getValue() + remainder;

      a.setValue(sum % 10);
      remainder = sum / 10;

      if (a.getNext() == null) {
        break;
      }
      if (b.getNext() == null) {
        break;
      }

      a = a.getNext();
      b = b.getNext();
    }

    return result;
  }

  //    public static SinglyLinkedList sumForward(SinglyLinkedList a, SinglyLinkedList b){
  //        SinglyLinkedList aPrevious = new SinglyLinkedList();
  //        SinglyLinkedList bPrevious = new SinglyLinkedList();;
  //
  //        // 1. go to end of list
  //        // 2. sum
  //        // 3. step back one
  //        // 4. repeat
  //        final SinglyLinkedList result = a;
  //
  //        while(true){
  //            // navigate to the end of the list
  //            if(a.getNext() == null) {
  //                break;
  //            }if(b.getNext() == null) {
  //                break;
  //            }
  //
  //            final SinglyLinkedList previousA;
  //            final SinglyLinkedList previousB;
  //
  //            aPrevious.setNext(previousA);
  //            bPrevious.setNext(previousB);
  //
  //            aPrevious = a;
  //            bPrevious = b;
  //
  //            a = a.getNext();
  //            b = b.getNext();
  //        }
  //
  //        // sum
  //        int remainder = 0;
  //        while(true){
  //            final int sum = a.getValue() + b.getValue() + remainder;
  //
  //            a.setValue(sum % 10);
  //            remainder = sum /10;
  //
  //            if(aPrevious.getNext() == null) {
  //                break;
  //            }if(bPrevious.getNext() == null) {
  //                break;
  //            }
  //
  //            a = aPrevious.getNext();
  //            b = bPrevious.getNext();
  //        }
  //
  //        return result;
  //    }
}
