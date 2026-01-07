package com.walterjwhite.serialization.api;

import java.io.OutputStream;

public abstract class SerializationOutputStream extends OutputStream {
  protected final Object data;

  protected SerializationOutputStream(Object data) {

    this.data = data;
  }
}
