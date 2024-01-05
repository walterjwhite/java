package com.walterjwhite.serialization.api;

import java.io.OutputStream;

/** Takes an object and writes to an output stream. */
public abstract class SerializationOutputStream extends OutputStream {
  protected final Object data;

  protected SerializationOutputStream(Object data) {

    this.data = data;
  }
}
