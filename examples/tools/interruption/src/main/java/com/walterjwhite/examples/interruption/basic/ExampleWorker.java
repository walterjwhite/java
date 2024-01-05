package com.walterjwhite.examples.interruption.basic;

import java.io.IOException;

// this is an example of how to use Process.destroyForcibly to kill a process
public class ExampleWorker {
  protected transient Process p;

  public int run() throws IOException, InterruptedException {
    p = Runtime.getRuntime().exec("sleep 100");
    return p.waitFor();
  }

  public void kill() {

    if (p == null) return;

    p.destroyForcibly();
  }
}
