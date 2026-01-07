package com.walterjwhite.email.javamail;

import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.EmailEmailAccount;
import com.walterjwhite.email.api.service.EmailSendService;
import com.walterjwhite.file.api.model.File;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.inject.Inject;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JavaMailEmailSendService implements EmailSendService /*ExternalEmailSendService*/ {



  @Override
  public void send(Email email) throws Exception {
    final Session session = JavaMailUtils.getSession(email.getFrom());



    MimeMessage message = new MimeMessage(session);

    message.setFrom(getFrom(email));

    for (EmailEmailAccount emailEmailAccount : email.getEmailEmailAccounts())
      addRecipient(
          message, emailEmailAccount.getEmailAccount(), emailEmailAccount.getEmailRecipientType());

    message.setSubject(email.getSubject());
    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText(email.getBody());

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    message.setContent(multipart);



    Transport.send(message);
  }

  protected InternetAddress getFrom(final Email email) throws AddressException {
    if (email.getFrom().getEmailAccount() != null) {
      return new InternetAddress(email.getFrom().getEmailAccount().getName());
    }

    return new InternetAddress(email.getFrom().getUsername());
  }

  protected void addRecipient(
      MimeMessage message, EmailAccount emailAccount, EmailRecipientType emailRecipientType)
      throws MessagingException {
    if (EmailRecipientType.To.equals(emailRecipientType))
      message.addRecipient(
          MimeMessage.RecipientType.TO, new InternetAddress(getEmailAddress(emailAccount)));
    else if (EmailRecipientType.Cc.equals(emailRecipientType))
      message.addRecipient(
          MimeMessage.RecipientType.CC, new InternetAddress(getEmailAddress(emailAccount)));
    else if (EmailRecipientType.Bcc.equals(emailRecipientType))
      message.addRecipient(
          MimeMessage.RecipientType.BCC, new InternetAddress(getEmailAddress(emailAccount)));
    else throw new UnsupportedOperationException("recipient type is unknown:");
  }

  public static String getEmailAddress(final EmailAccount emailAccount) {
    if (emailAccount.getEmailAddress() != null) {
      return emailAccount.getEmailAddress();
    }

    return emailAccount.getName();
  }


  protected int addAttachments(Multipart multipart, Email email, Set<File> files) throws Exception {
    if (files == null || files.isEmpty()) return (0);

    final Set<File> filesAdded = new HashSet<>();

    long totalAttachmentSize = 0;
    int index = 0;
    for (File attachmentFile : files) {

      final java.io.File f = new java.io.File(attachmentFile.getSource());


      totalAttachmentSize += attachmentFile.getSource().length();
      addAttachment(multipart, f);
      index++;

      filesAdded.add(attachmentFile);
    }

    files.removeAll(filesAdded);
    return (files.size() - index);
  }

  protected void addAttachment(Multipart multipart, final java.io.File file)
      throws MessagingException {
    BodyPart attachmentPart = new MimeBodyPart();

    DataSource source = new FileDataSource(file.getAbsolutePath());
    attachmentPart.setDataHandler(new DataHandler(source));
    attachmentPart.setFileName(file.getName());

    multipart.addBodyPart(attachmentPart);
  }
}
