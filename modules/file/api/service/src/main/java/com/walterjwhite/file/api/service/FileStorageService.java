package com.walterjwhite.file.api.service;

import com.walterjwhite.file.api.model.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface FileStorageService {
  File put(File file) throws IOException, NoSuchAlgorithmException;

  File put(java.io.File file) throws Exception;


  void get(File file) throws Exception;

  void delete(File file);
}
