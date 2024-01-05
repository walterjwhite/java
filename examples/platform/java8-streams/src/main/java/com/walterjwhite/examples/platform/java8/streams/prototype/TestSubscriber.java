package com.walterjwhite.examples.platform.java8.streams.prototype;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class TestSubscriber implements Subscriber<String> {
  @Override
  public void onSubscribe(Subscription s) {
    s.request(1);
  }

  @Override
  public void onNext(String s) {}

  @Override
  public void onError(Throwable t) {
    LOGGER.warn("error", t);
  }

  @Override
  public void onComplete() {}
}
