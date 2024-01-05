package com.walterjwhite.email.cli.provider;

import com.walterjwhite.email.cli.EmailAccountConfigurer;
import com.walterjwhite.email.cli.model.EmailOrganizationSession;
import com.walterjwhite.email.cli.property.EmailOrganizationSessionConfigurationDirectory;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class EmailOrganizationSessionProvider implements Provider<EmailOrganizationSession> {
  protected final EmailAccountConfigurer emailAccountConfigurer;
  protected final String emailOrganizationSessionConfigurationDirectory;
  protected final EmailOrganizationSession emailOrganizationSession;

  @Inject
  public EmailOrganizationSessionProvider(
      final EmailAccountConfigurer emailAccountConfigurer,
      @Property(EmailOrganizationSessionConfigurationDirectory.class)
          final String emailOrganizationSessionConfigurationDirectory) {
    this.emailAccountConfigurer = emailAccountConfigurer;
    this.emailOrganizationSessionConfigurationDirectory =
        emailOrganizationSessionConfigurationDirectory;

    emailOrganizationSession =
        emailAccountConfigurer.load(emailOrganizationSessionConfigurationDirectory);
  }

  @Override
  public EmailOrganizationSession get() {
    return emailOrganizationSession;
  }
}
