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
    /*
        final File sourceDirectory = new File("/tmp/test-dir");
        sourceDirectory.mkdirs();

        final File a = new File(sourceDirectory + "/a");
        final File b = new File(sourceDirectory + "/b");
        FileUtils.write(a, "Test a\n\n", Charset.defaultCharset());
        FileUtils.write(b, "Test b\n\n", Charset.defaultCharset());
        final String targetPath = "/tmp/target-test";
        final File tempTarget = new File(targetPath);

        final SFTPTransfer sftpTransfer =
            new SFTPTransfer(sshHost, sshUser, sourceDirectory.getAbsolutePath(), targetPath, 10);
        sftpTransferService.transfer(sftpTransfer);

        if (!sourceDirectory.exists()) LOGGER.warn("source does ! exist.");
        if (!tempTarget.exists()) LOGGER.warn("target does ! exist.");

        FileUtils.deleteDirectory(sourceDirectory);
    */
  }
}
