package com.walterjwhite.ssh.service;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.ssh.api.SFTPTransferService;
import com.walterjwhite.ssh.api.SSHPublicKeyPath;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import com.walterjwhite.ssh.executor.SFTPExecutor;
import jakarta.inject.Inject;

public class DefaultSFTPTransferService implements SFTPTransferService {

  protected final String sshPublicKeyPath;

  @Inject
  public DefaultSFTPTransferService(
      @Property(SSHPublicKeyPath.class) final String sshPublicKeyPath) {

    this.sshPublicKeyPath = sshPublicKeyPath;
  }

  @Override
  public void transfer(SFTPTransfer... transfers) throws Exception {
    for (SFTPTransfer transfer : transfers) transfer(transfer);
  }

  protected void transfer(SFTPTransfer transfer) throws Exception {
    new SFTPExecutor(sshPublicKeyPath, transfer).execute();
  }
}
