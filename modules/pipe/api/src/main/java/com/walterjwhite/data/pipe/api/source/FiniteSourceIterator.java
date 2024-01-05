package com.walterjwhite.data.pipe.api.source;

import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FiniteSourceIterator<Type> extends Spliterators.AbstractSpliterator<Type> {
  protected final Supplier<Type> supplier;

  public FiniteSourceIterator(int estimatedSize, Supplier<Type> supplier) {
    super(estimatedSize, Spliterator.ORDERED | Spliterator.DISTINCT);
    this.supplier = supplier;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Type> consumer) {
    try {
      Type next = supplier.get();
      consumer.accept(next);

      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
