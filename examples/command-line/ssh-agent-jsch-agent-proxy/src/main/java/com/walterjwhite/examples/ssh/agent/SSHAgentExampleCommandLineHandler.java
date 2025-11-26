package com.walterjwhite.examples.ssh.agent;

import com.spotify.sshagentproxy.AgentProxies;
import com.spotify.sshagentproxy.AgentProxy;
import com.spotify.sshagentproxy.Identity;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(onConstructor_ = @Inject)
public class SSHAgentExampleCommandLineHandler implements CommandLineHandler {
  @Override
  public void run(String... arguments) throws IOException {

    final byte[] dataToSign = {0xa, 0x2, (byte) 0xff};
    final AgentProxy agentProxy = AgentProxies.newInstance();
    final List<Identity> identities = agentProxy.list();
    for (final Identity identity : identities) {
      if (identity.getPublicKey().getAlgorithm().equals("RSA")) {
        LOGGER.info("signed data: {}", agentProxy.sign(identity, dataToSign));
      }
    }
  }
}
