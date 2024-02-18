package com.walterjwhite.email.modules.exchange.organization;

import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.EmailConversation;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeEmailConverterUtil {
    public static Email build(EmailMessage emailMessage) throws ServiceLocalException {
        final EmailConversation emailConversation = EmailConversation.builder()
                .uuid(emailMessage.getConversationId().getUniqueId()).build();

        final Email email = Email.builder()
                .messageId(emailMessage.getId().getUniqueId())
                .conversation(emailConversation)
                .subject(emailMessage.getSubject())
                .body(emailMessage.getBody().toString())
                .from(
                        new PrivateEmailAccount(
                                new EmailAccount().withEmailAddress(emailMessage.getFrom().getAddress())))
                .build();






        emailMessage
                .getToRecipients()
                .spliterator()
                .forEachRemaining(
                        emailAddress ->
                                addEmailAddress(email, emailAddress.getAddress(), EmailRecipientType.To));
        emailMessage
                .getCcRecipients()
                .spliterator()
                .forEachRemaining(
                        emailAddress ->
                                addEmailAddress(email, emailAddress.getAddress(), EmailRecipientType.Cc));
        emailMessage
                .getBccRecipients()
                .spliterator()
                .forEachRemaining(
                        emailAddress ->
                                addEmailAddress(email, emailAddress.getAddress(), EmailRecipientType.Bcc));

        return email;
    }

    private static void addEmailAddress(
            final Email email, final String emailAddress, final EmailRecipientType emailRecipientType) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            return;
        }







    }
}
