package com.walterjwhite.examples.platform.java8.streams.prototype;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class TestObservable extends Observable<String> {

  @Override
  protected void subscribeActual(Observer<? super String> observer) {}
}
