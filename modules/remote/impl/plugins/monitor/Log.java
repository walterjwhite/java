package com.walterjwhite.remote.impl.service.monitor;

import java.util.Collection;
import java.util.LinkedList;

public class Log<Type> extends LinkedList<Type> {

  protected final int maximumCapacity;

  protected boolean dirty;

  public Log(final int maximumCapacity) {

    this.maximumCapacity = maximumCapacity;
  }

  public Log() {
    this(512);
  }

  @Override
  public void add(int index, Type element) {
    if (size() >= maximumCapacity) {
      this.pop();
    }

    super.add(index, element);

    dirty = true;
  }

  @Override
  public boolean addAll(int index, Collection<? extends Type> c) {
    for (Type ce : c) {
      this.add(index, ce);
    }

    dirty = true;
    return true;
  }

  @Override
  public boolean addAll(Collection<? extends Type> c) {
    for (Type ce : c) {
      this.add(ce);
    }

    dirty = true;
    return true;
  }

  @Override
  public boolean add(Type e) {
    if (size() >= maximumCapacity) {
      this.pop();
    }

    dirty = true;
    return (super.add(e));
  }

  @Override
  public void addLast(Type e) {
    if (size() >= maximumCapacity) {
      this.pop();
    }

    dirty = true;
    super.addLast(e);
  }

  @Override
  public void addFirst(Type e) {
    if (size() >= maximumCapacity) {
      this.pop();
    }

    dirty = true;
    super.addFirst(e);
  }

  /*
      @Override
      public Type get(int index) {

          return super.get(index);
      }

      @Override
      public void forEach(Consumer<? super Type> action) {
          super.forEach(action); //To change body of generated methods, choose Tools | Templates.
      }

      public boolean isDirty() {
          return dirty;
      }

      public void setDirty(boolean dirty) {
          this.dirty = dirty;
      }
  */
}
