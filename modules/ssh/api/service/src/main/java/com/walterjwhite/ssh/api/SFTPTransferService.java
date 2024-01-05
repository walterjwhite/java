package com.walterjwhite.ssh.api;

import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;

public interface SFTPTransferService {
  void transfer(SFTPTransfer... transfers) throws Exception;
}
