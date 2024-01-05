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

    //    PublishProcessor<String> source = PublishProcessor.create();
    //
    //            source.doOnNext()
    //                    .observeOn(Schedulers.computation())
    //                    .subscribe(v -> compute(v), Main::logError);
    //
    //            for (int i = 0; i < 1_000_000; i++) {
    //                source.onNext(i);
    //            }
    //
    //            Thread.sleep(10_000);

  }
}
