package com.walterjwhite.data.pipe.modules.cli;

import com.walterjwhite.data.pipe.api.DataPipeConfiguration;
import com.walterjwhite.data.pipe.api.Pipe;
import com.walterjwhite.data.pipe.api.session.PipeSessionConfiguration;
import com.walterjwhite.data.pipe.api.session.PipeSessionGroupConfiguration;
import com.walterjwhite.data.pipe.impl.DefaultPipe;
import java.util.ArrayList;
import java.util.List;

public class PipeSessionInstance {
  protected final PipeSessionConfiguration pipeSessionConfiguration;

  protected final List<Pipe> pipes = new ArrayList<>();

  public PipeSessionInstance(final PipeSessionConfiguration pipeSessionConfiguration) {
    this.pipeSessionConfiguration = pipeSessionConfiguration;
  }

  public void configure() {
    for (PipeSessionGroupConfiguration pipeSessionGroupConfiguration :
        pipeSessionConfiguration.getGroups()) {
      for (DataPipeConfiguration dataPipeConfiguration :
          pipeSessionGroupConfiguration.getDataPipeConfigurations()) {
        pipes.add(
            new DefaultPipe(
                dataPipeConfiguration, pipeSessionGroupConfiguration, pipeSessionConfiguration));
      }
    }
  }

  public void start() {
    for (Pipe pipe : pipes) {
      pipe.start();
    }
  }

  protected void stop() {
    for (Pipe pipe : pipes) {
      //            pipe.close();
    }
  }
}
