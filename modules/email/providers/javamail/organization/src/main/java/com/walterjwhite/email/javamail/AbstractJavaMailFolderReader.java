package com.walterjwhite.email.javamail;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;


public abstract class AbstractJavaMailFolderReader extends AbstractJavaMail {
  protected transient String folderName;
  protected transient Folder folder;
  protected final transient JavaMailMessageToEmailFunction javaMailMessageToEmailFunction =
      new JavaMailMessageToEmailFunction();

  public void initialize(PrivateEmailAccount privateEmailAccount, String folderName)
      throws MessagingException {
    super.initialize(privateEmailAccount);

    this.folderName = folderName;

    folder = getFolder();
    folder.open(Folder.READ_ONLY);
  }

  protected Folder getFolder() throws MessagingException {
    if (folderName == null) return store.getDefaultFolder();

    return store.getFolder(folderName);
  }

  @Override
  public void close() {
    closeFolder();

    super.close();
  }

  protected void closeFolder() {
    if (folder != null) {

      try {

        folder.close();
      } catch (MessagingException e) {
        onMessagingException(e);
      }
    }
  }
}
