package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.remote.api.model.message.Message;


public class MessageReader implements CommandLineHandler {


  @Override
  public void run(final String... arguments) {
  }

  protected void displayMessage(Message message) {}
}
