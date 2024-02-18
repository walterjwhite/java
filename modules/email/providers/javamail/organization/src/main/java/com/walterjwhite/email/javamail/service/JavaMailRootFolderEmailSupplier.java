package com.walterjwhite.email.javamail.service;

import com.walterjwhite.email.api.annotation.EmailProvider;
import com.walterjwhite.email.api.enumeration.EmailProviderType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.api.service.EmailReadService;
import jakarta.inject.Inject;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import java.util.Spliterator;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(onConstructor_ = @Inject)
@EmailProvider(EmailProviderType.JavaMail)
public class JavaMailRootFolderEmailSupplier implements EmailReadService {

  protected final JavaMailEmailMessageReadService javaMailEmailMessageReadService;

  protected final boolean recursive;

  

  public void read(PrivateEmailAccount privateEmailAccount) {














  }

  public static final boolean isHoldsMessages(final Folder folder) throws MessagingException {
    return (folder.getType() & Folder.HOLDS_MESSAGES) != 0;
  }

  public static final boolean isRecurse(final boolean recursive, final Folder folder)
      throws MessagingException {
    return recursive && (folder.getType() & folder.HOLDS_FOLDERS) != 0;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Email> consumer) {
    return false;
  }

  @Override
  public Spliterator<Email> trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return 0;
  }

  @Override
  public int characteristics() {
    return Spliterator.DISTINCT + Spliterator.NONNULL;
  }
}
