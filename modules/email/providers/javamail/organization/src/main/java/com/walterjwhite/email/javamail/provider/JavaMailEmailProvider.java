package com.walterjwhite.email.javamail.provider;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.javamail.JavaMailMessageToEmailFunction;
import com.walterjwhite.email.organization.api.EmailProvider;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import java.util.Spliterator;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
public class JavaMailEmailProvider implements EmailProvider {
  protected final transient Store store;
  protected final transient Folder folder;
  protected final int pageSize;
  protected transient int index;
  protected transient int messageCount;

  protected final transient JavaMailMessageToEmailFunction javaMailMessageToEmailFunction =
      new JavaMailMessageToEmailFunction();











  @Override
  public boolean tryAdvance(Consumer<? super Email> consumer) {


    if (index < (messageCount - 1)) {
      try {
        consumer.accept(javaMailMessageToEmailFunction.apply(folder.getMessage(index++)));
        return true;
      } catch (MessagingException e) {
        throw new RuntimeException("Error fetching message", e);
      }
    }

    return false;
  }


  @Override
  public Spliterator<Email> trySplit() {

    return null;
  }

  @Override
  public long estimateSize() {
    return messageCount;
  }

  @Override
  public int characteristics() {
    return 0;
  }

  @Override
  public void reset() {
    index = 0;

    try {
      messageCount = folder.getMessageCount();
    } catch (MessagingException e) {
      throw new RuntimeException("Unable to determine message count", e);
    }
  }
}
