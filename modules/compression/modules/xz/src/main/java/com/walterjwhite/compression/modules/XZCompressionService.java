package com.walterjwhite.compression.modules;

import com.walterjwhite.encryption.api.service.CompressionService;
import jakarta.inject.Inject;
import java.io.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class XZCompressionService implements CompressionService {
  @Override
  public OutputStream getCompressionStream(final OutputStream outputStream) throws IOException {
    return new XZCompressorOutputStream(outputStream);
  }

  @Override
  public InputStream getDecompressionStream(final InputStream inputStream) throws IOException {
    return new XZCompressorInputStream(inputStream);
  }
}
