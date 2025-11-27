package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class MessageWriter implements CommandLineHandler {


  @Override
  public void run(final String... arguments) throws Exception {
    Thread.sleep(60000);
  }
}
