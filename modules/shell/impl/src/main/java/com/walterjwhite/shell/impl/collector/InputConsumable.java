package com.walterjwhite.shell.impl.collector;

import com.google.common.collect.ImmutableSet;
import com.walterjwhite.shell.api.service.OutputCollector;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputConsumable implements Runnable {
  protected final InputStream inputStream;
  protected final boolean isError;
  protected final ImmutableSet<OutputCollector> outputCollectors;

  public InputConsumable(InputStream inputStream, final OutputCollector... outputCollectors) {
    this(inputStream, false, outputCollectors);
  }

  public InputConsumable(
      InputStream inputStream, final boolean isError, final OutputCollector... outputCollectors) {

    this.inputStream = inputStream;
    this.isError = isError;

    this.outputCollectors = ImmutableSet.copyOf(outputCollectors);
  }

  @Override
  public void run() {
    new BufferedReader(new InputStreamReader(inputStream))
        .lines()
        .forEach(lineRead -> consumeOutput(lineRead));
  }

  
  
  protected void consumeOutput(final String line) {
    outputCollectors.forEach(outputCollector -> outputCollector.onData(line, isError));
  }
}
