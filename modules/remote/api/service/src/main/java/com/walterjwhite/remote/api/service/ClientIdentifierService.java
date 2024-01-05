package com.walterjwhite.remote.api.service;

import com.walterjwhite.shell.api.model.Node;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ClientIdentifierService {
  String get(Node node) throws IOException, NoSuchAlgorithmException;

  String get() throws Exception;
}
