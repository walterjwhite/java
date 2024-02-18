package com.walterjwhite.examples.email.deprecated;

import com.sun.mail.imap.IMAPFolder;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.api.service.EmailReadService;
import com.walterjwhite.email.javamail.service.JavaMailNewEmailSupplier;
import com.walterjwhite.email.javamail.service.example.JavaMailMessageCountListener;
import com.walterjwhite.email.javamail.service.example.Listener;
import com.walterjwhite.email.modules.javamail.JavaMailUtils;
import com.walterjwhite.email.organization.api.configuration.rule.*;
import com.walterjwhite.email.organization.plugins.count.CountAction;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeprecatedExample {

  protected final PrivateEmailAccount privateEmailAccount;
  protected final SerializationService serializationService;
  protected final EmailReadService emailReadService;
  protected transient JavaMailNewEmailSupplier javaMailNewEmailSupplier;

  protected void doSetup() throws MessagingException {
    this.javaMailNewEmailSupplier = new JavaMailNewEmailSupplier(privateEmailAccount, "INBOX");
  }

  protected void readInbox() {


    emailReadService.forEachRemaining(
        emailMessage -> LOGGER.info("read: " + emailMessage.getSubject()));
  }

  protected void readNewMessagesHackish() {
    try {
      final Store store = JavaMailUtils.getStore(JavaMailUtils.getSession(privateEmailAccount));

      final Folder folder = store.getFolder("INBOX");
      folder.open(Folder.READ_ONLY);
      folder.addMessageCountListener(new JavaMailMessageCountListener());

      final Listener listener = new Listener();
      listener.start((IMAPFolder) folder);

      try {
        Thread.sleep(10 * 60 * 1000);
      } catch (InterruptedException e) {
        LOGGER.error("error sleeping", e);
        Thread.currentThread().interrupt();
      }
    } catch (MessagingException e) {
      LOGGER.error("error getting store", e);
    }
  }

  
  protected void readNewMessages() {
    Stream.generate(javaMailNewEmailSupplier).forEach(email -> printEmail(email));
  }

  protected void printEmail(Email email) {}

  protected void writeSample() throws Exception {
    final EmailMatcherRule emailRules = new EmailMatcherRule();
    final GroupRule groupRule = new GroupRule();
    groupRule.setCriteriaType(CriteriaType.Should);
    final AttributeRule attributeRule = new AttributeRule();
    attributeRule.setEmailMessageField(EmailMessageField.Message);
    groupRule.getRules().add(attributeRule);
    groupRule.setInvert(true);
    emailRules.setRule(groupRule);
    emailRules.setActionClasses(new ArrayList<>());
    emailRules.getActionClasses().add(CountAction.class);

    serializationService.serialize(emailRules, new FileOutputStream("./output.yaml"));
  }
}
