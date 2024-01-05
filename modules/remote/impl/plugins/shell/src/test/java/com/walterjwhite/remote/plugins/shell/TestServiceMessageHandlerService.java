package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.shell.api.enumeration.ServiceAction;
import com.walterjwhite.shell.api.model.Service;

public class TestServiceMessageHandlerService {

  public static void main(final String[] arguments) {
    final ServiceMessage serviceMessage =
        new ServiceMessage((Client) null, -1, new Service("minidlna"), ServiceAction.Restart);
    //    final ServiceMessageHandlerService serviceMessageHandlerService =
    //        new ServiceMessageHandlerService();
    //
    //    serviceMessageHandlerService.process(serviceMessage);
  }
}
