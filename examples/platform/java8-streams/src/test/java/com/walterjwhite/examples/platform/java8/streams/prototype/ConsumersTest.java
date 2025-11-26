package com.walterjwhite.examples.platform.java8.streams.prototype;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ConsumersTest {
  @Test
  void test() {
    final String[] stringIterable = new String[] {"Fred", "John"};
    final Disposable o1 = Observable.fromArray(stringIterable).subscribe(new TestConsumer("a"));
    final Disposable o2 = Observable.fromArray(stringIterable).subscribe(new TestConsumer("b"));
    final Disposable o3 = Observable.fromArray(stringIterable).subscribe(new TestConsumer("c"));


  }
}
