package com.walterjwhite.email.javamail.service;


import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.api.service.FileEntityOutputStream;
import com.walterjwhite.file.api.service.FileStorageService;
import jakarta.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.transaction.Transactional;
import org.apache.commons.io.IOUtils;

public class JavaMailEmailMessageReadService {


  protected final FileStorageService fileStorageService;

  @Inject
  public JavaMailEmailMessageReadService(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @Transactional
  public Email store(Email transientEmail) throws IOException {
    transientEmail.setFrom(getFrom(transientEmail.getFrom()));





    final Iterator<File> fileIterator = transientEmail.getFiles().iterator();
    while (fileIterator.hasNext()) {
      final File transientFile = fileIterator.next();
      final File persistentFile = getAttachment(transientFile);

      transientEmail.getFiles().remove(transientFile);
      transientEmail.getFiles().add(persistentFile);
    }












    

    return transientEmail;
  }

  protected PrivateEmailAccount getFrom(PrivateEmailAccount transientFromEmailAccount) {






    return transientFromEmailAccount;
  }

  protected EmailAccount getEmailAccount(EmailAccount transientEmailAccount) {









    return transientEmailAccount;
  }

  protected com.walterjwhite.file.api.model.File getAttachment(
      com.walterjwhite.file.api.model.File transientFile) throws IOException {
    try (final InputStream is = null ) {
      try (final FileEntityOutputStream feos = new FileEntityOutputStream(fileStorageService)) {
        IOUtils.copy(is, feos);
        feos.flush();

        com.walterjwhite.file.api.model.File file = feos.getFile();
        file.withFilename(transientFile.getName() );

        return file;
      }
    }
  }







}
