package com.walterjwhite.examples.ssh.agent;

import com.spotify.sshagentproxy.AgentProxies;
import com.spotify.sshagentproxy.AgentProxy;
import com.spotify.sshagentproxy.Identity;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import java.io.IOException;
import java.util.List;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(onConstructor_ = @Inject)
public class SSHAgentExampleCommandLineHandler implements CommandLineHandler {
  //  @Inject
  //  public SSHAgentExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) throws IOException {

    final byte[] dataToSign = {0xa, 0x2, (byte) 0xff};
    final AgentProxy agentProxy = AgentProxies.newInstance();
    final List<Identity> identities = agentProxy.list();
    for (final Identity identity : identities) {
      if (identity.getPublicKey().getAlgorithm().equals("RSA")) {
        final byte[] signedData = agentProxy.sign(identity, dataToSign);
      }
    }
  }
}
