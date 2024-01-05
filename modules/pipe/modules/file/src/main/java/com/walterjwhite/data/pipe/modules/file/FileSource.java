package com.walterjwhite.data.pipe.modules.file;

import com.walterjwhite.data.pipe.impl.AbstractSource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class FileSource extends AbstractSource<String, FileSourceConfiguration> {
  protected BufferedReader bufferedReader;

  @Override
  protected void doConfigure() {
    try {
      bufferedReader = new BufferedReader(new FileReader(sourceConfiguration.getPath()));
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Error initializing file source", e);
    }
  }

  @Override
  public void close() throws Exception {
    if (bufferedReader != null) bufferedReader.close();
  }

  @Override
  public Iterator<String> iterator() {
    return bufferedReader.lines().iterator();
  }
}
