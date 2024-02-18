package com.walterjwhite.data.pipe.modules.email.javamail.utils;

import com.walterjwhite.file.api.service.FileEntityOutputStream;
import com.walterjwhite.file.api.service.FileStorageService;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.Part;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class AttachmentUtils {
  private AttachmentUtils() {}

  public static boolean isAttachment(BodyPart bodyPart) throws MessagingException {
    return Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
        && (bodyPart.getFileName() != null && !bodyPart.getFileName().isEmpty());
  }




  public static com.walterjwhite.file.api.model.File getAttachment(
      FileStorageService fileStorageService, BodyPart bodyPart)
      throws IOException, MessagingException {
    try (final InputStream is = bodyPart.getInputStream()) {
      try (final FileEntityOutputStream feos = new FileEntityOutputStream(fileStorageService)) {
        IOUtils.copy(is, feos);
        feos.flush();

        com.walterjwhite.file.api.model.File file = feos.getFile();
        file.withFilename(bodyPart.getFileName());

        return file;
      }
    }
  }
}
