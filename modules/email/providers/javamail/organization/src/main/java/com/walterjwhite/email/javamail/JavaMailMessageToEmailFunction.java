package com.walterjwhite.email.javamail;


import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.impl.EmailAddressUtil;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;


public class JavaMailMessageToEmailFunction implements Function<Message, Email> {
  public static final String MESSAGE_ID_HEADER = "Message-ID";

  @Override
  @SneakyThrows
  public Email apply(final Message message) {
    final Email.EmailBuilder emailBuilder = Email.builder()
            .from(getFrom(message))
            .messageId(getMessageId(message))
            .subject(message.getSubject())
            .createdDate(
                    LocalDateTime.ofInstant(message.getReceivedDate().toInstant(), ZoneId.systemDefault()));

    try {
      emailBuilder.emailEmailAccounts(JavaMailContactUtil.addEmailContacts(message));




      if (message.isMimeType("text/plain")) {
        emailBuilder.body(message.getContent().toString());
      } else if (message.isMimeType("multipart/*")) {
        emailBuilder.body(JavaMailPartUtil.getBody((MimeMultipart) message.getContent()));
      }

      JavaMailPartUtil.handleParts(emailBuilder, message);
      return emailBuilder.build();
    } catch (MessagingException | IOException e) {
      throw new RuntimeException("Error converting message", e);
    }
  }

  @SneakyThrows
  protected PrivateEmailAccount getFrom(Message message) {
    if (message.getFrom() == null || message.getFrom().length == 0) {
      return null;
    }

    return new PrivateEmailAccount(JavaMailContactUtil.getEmailAccount(message.getFrom()[0]));
  }

  @SneakyThrows
  public static String getMessageId(Message message)  {
    if (message == null) {
      return null;
    }

    final String[] headers = message.getHeader(MESSAGE_ID_HEADER);
    if (headers == null || headers.length == 0) {
      return null;
    }

    return headers[0];
  }












}
