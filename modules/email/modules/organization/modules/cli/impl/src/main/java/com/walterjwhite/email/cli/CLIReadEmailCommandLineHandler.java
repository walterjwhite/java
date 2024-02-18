package com.walterjwhite.email.cli;

import com.walterjwhite.email.cli.model.EmailAccountRules;
import com.walterjwhite.email.cli.model.EmailOrganizationSession;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.queue.api.service.ForkJoinWork;
import jakarta.inject.Inject;
import java.util.concurrent.TimeUnit;

public class CLIReadEmailCommandLineHandler implements CommandLineHandler {
  protected final ForkJoinWork forkJoinWork = new ForkJoinWork();
  protected final EmailOrganizationSession emailOrganizationSession;

  @Inject
  public CLIReadEmailCommandLineHandler(

      EmailOrganizationSession emailOrganizationSession) {

    this.emailOrganizationSession = emailOrganizationSession;
  }

  @Override
  public void run(String... arguments) throws InterruptedException {
    emailOrganizationSession.getEmailAccountRules().stream()
        .forEach(emailAccountRules -> organizeEmails(emailAccountRules));

    
    forkJoinWork.waitForAll( 1, TimeUnit.MINUTES);
  }

  protected void organizeEmails(EmailAccountRules emailAccountRules) {
    forkJoinWork.submit(new EmailAccountRulesRunnable(emailAccountRules));
  }
}
