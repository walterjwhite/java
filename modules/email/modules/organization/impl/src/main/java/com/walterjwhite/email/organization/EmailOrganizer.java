package com.walterjwhite.email.organization;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.organization.api.Action;
import com.walterjwhite.email.organization.api.configuration.rule.EmailMatcherRule;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EmailOrganizer {
  protected final String folderName;
  protected final PrivateEmailAccount privateEmailAccount;
  protected final Supplier<Email> newEmailSupplier;
  protected final List<EmailMatcherRule> emailMatcherRules;

  public void process() {
    Stream.generate(newEmailSupplier).forEach(emailMessage -> run(emailMessage));
  }

  protected void run(Email email) {
    for (EmailMatcherRule emailMatcherRule : emailMatcherRules) {
      try {
        if (emailMatcherRule.matches(email)) {
          executeActions(emailMatcherRule, email);
          break;
        }
      } catch (Exception e) {
        onExecutionError(e);
      }
    }
  }

  protected void executeActions(EmailMatcherRule emailMatcherRule, Email email) {
    emailMatcherRule.initializeActionClasses();

    emailMatcherRule.getActionClasses().stream()
        .forEach(actionClass -> executeAction(emailMatcherRule, email, actionClass));
  }

  protected void executeAction(
      EmailMatcherRule emailMatcherRule, Email email, final Class<? extends Action> actionClass) {
    try {
      final Action action = getActionInstance(actionClass);
      action.execute(folderName, privateEmailAccount, emailMatcherRule, email);
    } catch (Exception e) {
      LOGGER.warn("Error executingAction", e);
    }
  }

  protected void onExecutionError(Exception e) {}

  protected Action getActionInstance(final Class<? extends Action> actionClass) {
    return ApplicationHelper.getApplicationInstance().getInjector().getInstance(actionClass);
  }
}
