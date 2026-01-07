package com.walterjwhite.logging.agent;

import com.walterjwhite.inject.cli.AgentApplicationInstance;
import com.walterjwhite.inject.cli.AgentHandler;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;

@Slf4j
public class LoggingAgentHandler implements AgentHandler {

  @Override
  public void initialize(final AgentApplicationInstance agentApplicationInstance) {
    final AgentBuilder agentBuilder =
        new AgentBuilder.Default().with(AgentBuilder.RedefinitionStrategy.REDEFINITION);

    agentBuilder.installOn(agentApplicationInstance.getInstrumentation());
  }
}
