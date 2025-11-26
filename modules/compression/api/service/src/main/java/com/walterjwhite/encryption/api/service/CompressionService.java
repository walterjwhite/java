package com.walterjwhite.encryption.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.utils.IOUtils;

public interface CompressionService {
  default void compress(InputStream inputStream, OutputStream outputStream) throws IOException {
    try (final OutputStream compressionOutputStream = getCompressionStream(outputStream)) {
      IOUtils.copy(inputStream, compressionOutputStream);
    }
  }

  default void decompress(InputStream inputStream, OutputStream outputStream) throws IOException {
    try (final InputStream compressionInputStream = getDecompressionStream(inputStream)) {
      IOUtils.copy(compressionInputStream, outputStream);
    }
  }

  OutputStream getCompressionStream(final OutputStream outputStream) throws IOException;

  InputStream getDecompressionStream(final InputStream inputStream) throws IOException;
}
