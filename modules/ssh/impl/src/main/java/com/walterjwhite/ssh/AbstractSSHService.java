package com.walterjwhite.ssh;

import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import java.io.File;
import java.io.IOException;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

public abstract class AbstractSSHService<SSHEntityType extends AbstractSSHEntity> {
  protected final String sshPublicKeyPath;
  protected final SSHEntityType command;

  protected AbstractSSHService(final String sshPublicKeyPath, SSHEntityType command) {

    this.command = command;
    this.sshPublicKeyPath = sshPublicKeyPath;
  }

  protected Session setupSSH(final SSHClient sshClient, final String host, final String username)
      throws IOException {
    sshClient.loadKnownHosts();

    // AUTOMATICALLY TRUST THE HOST ...
    sshClient.addHostKeyVerifier(new PromiscuousVerifier());

    sshClient.connect(host);

    // LOGGER.warn("user:" + username);

    // sshClient.authPublickey(System.getProperty("user.name"), publicKeyLocation);
    sshClient.authPublickey(username, getPublicKeyPath());

    return sshClient.startSession();
  }

  // that is most certainly not true
  protected String getPublicKeyPath() {
    if (sshPublicKeyPath != null && !sshPublicKeyPath.isEmpty()) return sshPublicKeyPath;

    return getDefaultPublicKeyPath();
  }

  protected String getDefaultPublicKeyPath() {
    return System.getProperty("user.home") + File.separator + ".ssh" + File.separator + "id_ecdsa";
  }

  public void execute() throws Exception {
    try (SSHClient sshClient = new SSHClient()) {
      try (Session session =
          setupSSH(sshClient, command.getHost().getName(), command.getUser().getName())) {
        doExecute(sshClient, session);
      }
    }
  }

  protected abstract void doExecute(SSHClient sshClient, Session session) throws Exception;
}
