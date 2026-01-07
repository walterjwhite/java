package com.walterjwhite.email.javamail;

import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.EmailEmailAccount;
import com.walterjwhite.email.api.util.EmailAddressUtil;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaMailContactUtil {
  public static Set<EmailEmailAccount> addEmailContacts(final Message message)
      throws MessagingException {
    final Set<EmailEmailAccount> emailEmailAccounts = new HashSet<>();

    createEmailContacts(
        emailEmailAccounts, message, Message.RecipientType.TO, EmailRecipientType.To);
    createEmailContacts(
        emailEmailAccounts, message, Message.RecipientType.CC, EmailRecipientType.Cc);
    createEmailContacts(
        emailEmailAccounts, message, Message.RecipientType.BCC, EmailRecipientType.Bcc);

    return emailEmailAccounts;
  }

  public static void createEmailContacts(
      final Set<EmailEmailAccount> emailEmailAccounts,
      final Message message,
      final Message.RecipientType recipientType,
      final EmailRecipientType emailRecipientType)
      throws MessagingException {

    if (message.getRecipients(recipientType) != null) {
      for (final Address address : message.getRecipients(recipientType)) {
        try {
          emailEmailAccounts.add(
              new EmailEmailAccount(getEmailAccount(address), null, emailRecipientType));
        } catch (IllegalArgumentException e) {
          handleInvalidEmailAddress(e);
        }
      }
    }
  }

  public static EmailAccount getEmailAccount(final Address address) {
    final String[] emailAddressParts = EmailAddressUtil.getEmailAddressParts(address.toString());
    return new EmailAccount().withEmailAddress(emailAddressParts[1]).withName(emailAddressParts[0]);
  }

  public static void handleInvalidEmailAddress(IllegalArgumentException e) {}
}
