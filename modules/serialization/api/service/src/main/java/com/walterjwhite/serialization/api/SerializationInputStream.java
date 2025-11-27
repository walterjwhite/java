package com.walterjwhite.serialization.api;

import java.io.InputStream;

public abstract class SerializationInputStream extends InputStream {
  protected final InputStream inputStream;

  protected SerializationInputStream(InputStream inputStream) {

    this.inputStream = inputStream;
  }

  protected abstract Object deserialize();
}
