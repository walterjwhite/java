package com.walterjwhite.serialization.api;

import java.io.InputStream;

/** Takes an input stream and writes the object back */
public abstract class SerializationInputStream extends InputStream {
  protected final InputStream inputStream;

  protected SerializationInputStream(InputStream inputStream) {

    this.inputStream = inputStream;
  }

  protected abstract Object deserialize();
}
