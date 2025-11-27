package com.walterjwhite.remote.plugins.shell;

import com.walterjwhite.shell.api.enumeration.ServiceAction;
import com.walterjwhite.shell.api.model.Service;

public class TestServiceMessageHandlerService {

  public static void main(final String[] arguments) {
    final ServiceMessage serviceMessage = new ServiceMessage();
    serviceMessage.setService(new Service());
    serviceMessage.setServiceAction(ServiceAction.Restart);

  }
}
