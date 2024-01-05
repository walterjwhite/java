package com.walterjwhite.encryption.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface CompressionService {
  OutputStream getCompressionStream(final OutputStream outputStream) throws IOException;

  InputStream getDecompressionStream(final InputStream inputStream) throws IOException;
}
