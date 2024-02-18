package com.walterjwhite.data.pipe.modules.email.javamail.transformation;

import com.walterjwhite.data.pipe.modules.email.javamail.model.JavaMailMessage;
import com.walterjwhite.data.pipe.modules.email.javamail.source.JavaMailMessageIterator;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JavaMailFolderToJavaMailMessageTransformation
    implements Function<Folder, Stream<JavaMailMessage>> {
  @Override
  public Stream<JavaMailMessage> apply(Folder folder) {
    if (folder == null) {
      return Stream.empty();
    }

    try {
      folder.open(Folder.READ_ONLY);
      return StreamSupport.stream(new JavaMailMessageIterator(folder), false);
    } catch (MessagingException e) {
      throw new RuntimeException("Error processing folder", e);
    }
  }
}
