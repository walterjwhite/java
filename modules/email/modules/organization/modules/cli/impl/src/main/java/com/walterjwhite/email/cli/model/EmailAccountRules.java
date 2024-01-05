package com.walterjwhite.email.cli.model;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.cli.EmailOrganizationRunnable;
import com.walterjwhite.email.cli.property.CleanupType;
import com.walterjwhite.email.organization.EmailOrganizer;
import com.walterjwhite.email.organization.api.configuration.rule.EmailMatcherRule;
import com.walterjwhite.queue.api.service.ForkJoinWork;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString(doNotUseGetters = true)
public class EmailAccountRules implements Serializable {
  // protected String folderName;
  protected CleanupType cleanupType;
  protected PrivateEmailAccount emailAccount;
  protected String folderName;
  @ToString.Exclude protected List<EmailMatcherRule> emailMatcherRules;

  //  protected transient EmailOrganizer emailOrganizer;

  @SneakyThrows
  public void process() {
    final EmailOrganizer[] emailOrganizers =
        cleanupType.build(emailAccount, folderName, emailMatcherRules);

    final ForkJoinWork forkJoinWork = new ForkJoinWork();

    for (final EmailOrganizer emailOrganizer : emailOrganizers) {
      try {
        forkJoinWork.submit(new EmailOrganizationRunnable(emailOrganizer));
      } catch (Exception e) {
        LOGGER.warn("Error waiting for all to complete", e);
      }
    }

    forkJoinWork.waitForAll(1, TimeUnit.MINUTES);
  }
}
