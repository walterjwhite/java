package com.walterjwhite.remote.impl.service;

import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import com.walterjwhite.remote.api.service.ClientIdentifierService;
import com.walterjwhite.shell.api.model.Node;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultClientIdentifierService implements ClientIdentifierService {
  protected final DigestAlgorithm digestAlgorithm;
  protected final Node node;

  @Override
  public String get(Node node) throws IOException, NoSuchAlgorithmException {
    return digestAlgorithm.compute(node.getUuid()).substring(55);
  }

  @Override
  public String get() throws Exception {
    return get(node);
  }
}
