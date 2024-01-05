package com.walterjwhite.modules.shell.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.ssh.api.SFTPTransferService;
import com.walterjwhite.ssh.api.SSHCommandService;
import com.walterjwhite.ssh.service.DefaultSFTPTransferService;
import com.walterjwhite.ssh.service.DefaultSSHCommandService;

public class SSHModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(SSHCommandService.class).to(DefaultSSHCommandService.class);
    bind(SFTPTransferService.class).to(DefaultSFTPTransferService.class);
  }
}
