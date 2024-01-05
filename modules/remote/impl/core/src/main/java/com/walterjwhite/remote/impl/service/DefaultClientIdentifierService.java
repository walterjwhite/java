package com.walterjwhite.remote.impl.service;

import com.walterjwhite.encryption.service.DigestService;
import com.walterjwhite.remote.api.service.ClientIdentifierService;
import com.walterjwhite.shell.api.model.Node;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import jakarta.inject.Inject;

public class DefaultClientIdentifierService implements ClientIdentifierService {
  protected final DigestService digestService;
  protected final Node node;

  @Inject
  public DefaultClientIdentifierService(DigestService digestService, Node node) {

    this.digestService = digestService;
    this.node = node;
  }

  /**
   * Keep only the last 8 bytes.
   *
   * @throws IOException
   * @throws NoSuchAlgorithmException
   */
  @Override
  public String get(Node node) throws IOException, NoSuchAlgorithmException {
    return digestService
        .computeSignature(
            new ByteArrayInputStream(node.getUuid().getBytes(Charset.defaultCharset())))
        .substring(55);
  }

  @Override
  public String get() throws Exception {
    return get(node);
  }
}
