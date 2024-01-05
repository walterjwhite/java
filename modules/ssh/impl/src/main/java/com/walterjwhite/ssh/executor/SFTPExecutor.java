package com.walterjwhite.ssh.executor;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.ssh.AbstractSSHService;
import com.walterjwhite.ssh.api.SSHPublicKeyPath;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.xfer.FileSystemFile;
import net.schmizz.sshj.xfer.scp.SCPFileTransfer;

public class SFTPExecutor extends AbstractSSHService<SFTPTransfer> {
  public SFTPExecutor(
      @Property(SSHPublicKeyPath.class) final String sshPublicKeyPath, SFTPTransfer command) {
    super(sshPublicKeyPath, command);
  }

  @Override
  protected void doExecute(SSHClient sshClient, Session session) throws Exception {
    final SCPFileTransfer scpFileTransfer = sshClient.newSCPFileTransfer();

    if (command.isUpload())
      scpFileTransfer.upload(new FileSystemFile(command.getLocalPath()), command.getRemotePath());
    else
      scpFileTransfer.download(command.getRemotePath(), new FileSystemFile(command.getLocalPath()));
  }
}
