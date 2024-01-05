package com.walterjwhite.email.javamail;

import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.EmailEmailAccount;
import com.walterjwhite.email.api.service.EmailSendService;
import com.walterjwhite.file.api.model.File;
import java.util.HashSet;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import jakarta.inject.Inject;
import javax.mail.*;
import javax.mail.internet.*;
import lombok.RequiredArgsConstructor;

// @ServiceSupports(EmailProviderType.Default)
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JavaMailEmailSendService implements EmailSendService /*ExternalEmailSendService*/ {
  //  protected final int maximumAttachmentSize;

  //  protected final FileStorageService fileStorageService;

  //  @Inject
  //  public JavaMailEmailSendService(
  //      @Property(MaximumAttachmentSize.class) int maximumAttachmentSize,
  //      FileStorageService fileStorageService) {
  //
  //    this.maximumAttachmentSize = maximumAttachmentSize;
  ////    this.fileStorageService = fileStorageService;
  //  }

  @Override
  public void send(Email email) throws Exception {
    final Session session = JavaMailUtils.getSession(email.getFrom());

    //    final Set<File> remainingAttachments = new HashSet<>();
    //    if (email.getFiles() != null && !email.getFiles().isEmpty())
    //      remainingAttachments.addAll(email.getFiles());

    //    int previousRemainingAttachments;
    //    do {
    //      previousRemainingAttachments = remainingAttachments.size();

    // AutoCreate a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

    // Set From: header field of the header.
    message.setFrom(getFrom(email));

    // use from to setup authentication (if required)
    for (EmailEmailAccount emailEmailAccount : email.getEmailEmailAccounts())
      addRecipient(
          message, emailEmailAccount.getEmailAccount(), emailEmailAccount.getEmailRecipientType());

    message.setSubject(email.getSubject());
    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText(email.getBody());

    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    message.setContent(multipart);

    //      addAttachments(multipart, email, remainingAttachments);

    // message.setText(email.getBody());

    // Send message
    Transport.send(message);
    //      if (previousRemainingAttachments > 0
    //          && previousRemainingAttachments == remainingAttachments.size()) {
    //        // throw(new RuntimeException("One attachment is too large"));
    //        throw new RuntimeException("One attachment is too large");
    //      }
    //    } while (remainingAttachments.size() > 0);
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

  //  protected int addAttachments(Multipart multipart, Email email) throws MessagingException {
  //    return (addAttachments(multipart, email, null));
  //  }

  protected int addAttachments(Multipart multipart, Email email, Set<File> files) throws Exception {
    if (files == null || files.isEmpty()) return (0);

    final Set<File> filesAdded = new HashSet<>();

    long totalAttachmentSize = 0;
    int index = 0;
    for (File attachmentFile : files) {
      // disabled - 2021/10/28
      //      fileStorageService.get(attachmentFile);

      final java.io.File f = new java.io.File(attachmentFile.getSource());

      // disabled - 2021/10/28
      //      if (totalAttachmentSize + f.length() > maximumAttachmentSize) {
      //        // LOGGER.info("total attachment size:" + totalAttachmentSize + ":" +
      //        // maximumAttachmentSize);
      //        // break;
      //        throw new RuntimeException(
      //            "Total attachment size:"
      //                + totalAttachmentSize
      //                + " is greater than the maximum size of: "
      //                + maximumAttachmentSize);
      //      }

      totalAttachmentSize += attachmentFile.getSource().length();
      addAttachment(multipart, f);
      index++;

      filesAdded.add(attachmentFile);
    }

    // remove this from the files we must add
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
