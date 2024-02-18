package com.walterjwhite.email.javamail;

import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.EmailEmailAccount;
import com.walterjwhite.email.impl.EmailAddressUtil;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaMailPartUtil {
    public static void handleParts(final Email.EmailBuilder emailBuilder, final Message message) throws IOException, MessagingException {
        final Object content = message.getContent();
        if (content instanceof Multipart) {
            final Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);

                handlePart(emailBuilder, bodyPart);
            }
        }
    }

    public static void handlePart(final Email.EmailBuilder emailBuilder, BodyPart bodyPart) throws MessagingException, IOException {
        if (isAttachment(bodyPart)) {
            handleAttachment(emailBuilder, bodyPart);
        } else {
            setBody(emailBuilder, bodyPart);
        }
    }

    public static void handleAttachment(final Email.EmailBuilder emailBuilder, BodyPart bodyPart)
            throws IOException, MessagingException {

    }

    public static boolean isAttachment(BodyPart bodyPart) throws MessagingException {
        return Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
                && (bodyPart.getFileName() != null && !bodyPart.getFileName().isEmpty());
    }

    public static String getBody(MimeMultipart mimeMultipart) throws MessagingException, IOException {
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

    public static void setBody(final Email.EmailBuilder emailBuilder, BodyPart bodyPart) throws MessagingException, IOException {
        final String body = bodyPart.getContent().toString();
        if (body != null) {
            emailBuilder.body(body);
            return;
        }

        emailBuilder.body(IOUtils.toString(bodyPart.getInputStream(), Charset.defaultCharset()));
    }


    public static com.walterjwhite.file.api.model.File getAttachment(final BodyPart bodyPart) {













        return null;
    }
}
