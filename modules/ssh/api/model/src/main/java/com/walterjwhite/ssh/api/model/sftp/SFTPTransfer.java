package com.walterjwhite.ssh.api.model.sftp;

import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class SFTPTransfer extends AbstractSSHEntity {

  protected String localPath;

  protected String remotePath;

  @EqualsAndHashCode.Exclude protected int timeout;

  protected boolean upload = true;

  public SFTPTransfer(SSHHost host, SSHUser user, String localPath, String remotePath) {
    this(host, user, localPath, remotePath, -1);
  }

  public SFTPTransfer(
      SSHHost host, SSHUser user, String localPath, String remotePath, int timeout) {
    super(host, user);
    this.localPath = localPath;
    this.remotePath = remotePath;
    this.timeout = timeout;
  }
}
