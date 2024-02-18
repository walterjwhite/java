package com.walterjwhite.data.pipe.modules.email.javamail.utils;

import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

public class MessageUtils {
  private MessageUtils() {}

  public static String getServerId(Message message) throws MessagingException {
    if (message == null) return null;

    final String[] headers = message.getHeader("Message-ID");
    if (headers == null || headers.length == 0) return null;

    return headers[0];
  }

  public static String getBody(BodyPart bodyPart) throws IOException, MessagingException {
    if (bodyPart.getContent().toString() != null) return bodyPart.getContent().toString();

    return IOUtils.toString(bodyPart.getInputStream(), Charset.defaultCharset());
  }

  public static String getBody(
       Message message) throws MessagingException, IOException {
    if (message.isMimeType("text/plain")) {
      return message.getContent().toString();
    }

    if (message.isMimeType("multipart/*")) {
      return getBody((MimeMultipart) message.getContent());
    }

    return null;
  }

  private static String getBody(MimeMultipart mimeMultipart)
      throws MessagingException, IOException {
    StringBuilder buffer = new StringBuilder();
    int count = mimeMultipart.getCount();
    for (int i = 0; i < count; i++) {
      BodyPart bodyPart = mimeMultipart.getBodyPart(i);
      if (bodyPart.isMimeType("text/plain")) {
        buffer.append("\n" + bodyPart.getContent());
        break; 
      } else if (bodyPart.isMimeType("text/html")) {
        String html = (String) bodyPart.getContent();

        buffer.append("\n" + html);
      } else if (bodyPart.getContent() instanceof MimeMultipart) {
        buffer.append(getBody((MimeMultipart) bodyPart.getContent()));
      }
    }

    return buffer.toString();
  }
}
