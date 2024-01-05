package com.walterjwhite.data.pipe.modules.file;

import com.walterjwhite.data.pipe.impl.AbstractSource;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

public class DirectorySource extends AbstractSource<File, FileSourceConfiguration> {
  protected File directory;

  @Override
  protected void doConfigure() {
    directory = new File(sourceConfiguration.getPath());
  }

  @Override
  public void close() {}

  @Override
  public Iterator<File> iterator() {
    return Arrays.asList(directory.listFiles()).iterator();
  }
}
