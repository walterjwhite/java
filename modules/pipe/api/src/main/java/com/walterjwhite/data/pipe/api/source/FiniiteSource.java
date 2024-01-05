package com.walterjwhite.data.pipe.api.source;

import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FiniiteSource<Type> implements Spliterator<Type> {
  protected final Supplier<Type> supplier;

  public FiniiteSource(Supplier<Type> supplier) {
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

  @Override
  public Spliterator<Type> trySplit() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public long estimateSize() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int characteristics() {
    return Spliterator.ORDERED;
    // return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.IMMUTABLE |
    // Spliterator.SUBSIZED;
  }
}
