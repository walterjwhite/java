package com.walterjwhite.data.pipe.impl;

import java.util.Iterator;
import java.util.function.Supplier;

public class DefaultSupplier<Type> implements Supplier<Type> {
  protected final Iterator<Type> iterator;

  public DefaultSupplier(final Iterator<Type> iterator) {
    this.iterator = iterator;
  }

  @Override
  public Type get() {
    return iterator.next();
  }
}
