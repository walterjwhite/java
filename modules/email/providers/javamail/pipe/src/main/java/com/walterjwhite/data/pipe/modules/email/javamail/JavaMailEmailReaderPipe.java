package com.walterjwhite.data.pipe.modules.email.javamail;

import com.walterjwhite.data.pipe.modules.email.javamail.sink.EmailJPASink;
import com.walterjwhite.data.pipe.modules.email.javamail.source.JavaMailFolderIterator;
import com.walterjwhite.data.pipe.modules.email.javamail.transformation.JavaMailFolderToJavaMailMessageTransformation;
import com.walterjwhite.data.pipe.modules.email.javamail.transformation.JavaMailToEmailTransformation;
import com.walterjwhite.data.pipe.modules.email.javamail.utils.StoreUtils;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.file.api.service.FileStorageService;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import java.util.stream.StreamSupport;

public class JavaMailEmailReaderPipe {
  protected final Provider<EntityManager> entityManagerProvider;
  protected final FileStorageService fileStorageService;

  @Inject
  public JavaMailEmailReaderPipe(
      Provider<EntityManager> entityManagerProvider, FileStorageService fileStorageService) {
    this.entityManagerProvider = entityManagerProvider;
    this.fileStorageService = fileStorageService;
  }

  

  public void run(PrivateEmailAccount emailAccount) throws MessagingException {
    final Store store = StoreUtils.getStore(emailAccount);



    StreamSupport.stream(
            new JavaMailFolderIterator(store, store.getDefaultFolder().list("*")), true)
        .flatMap(new JavaMailFolderToJavaMailMessageTransformation())
        .map(new JavaMailToEmailTransformation(fileStorageService))
        .forEach(new EmailJPASink(entityManagerProvider));
  }
}
