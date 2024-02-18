package com.walterjwhite.ssh.service;

import com.walterjwhite.ssh.api.SFTPTransferService;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SFTPTest {

  protected SFTPTransferService sftpTransferService;

  protected SSHHost sshHost;
  protected Set<SSHHost> sshHosts;
  protected SSHUser sshUser;

  @BeforeAll
  public void before() {
    sshHost = new SSHHost("localhost");
    sshHosts = new HashSet<>();
    sshHosts.add(sshHost);
    sshUser = new SSHUser("user", null, sshHosts);

    System.setProperty("ssh.public-key", "/home/user/.ssh/java_id_ecdsa");
  }

  @Test
  public void testSomething() {
    
  }
}
