package com.walterjwhite.remote.api.repository;

import com.walterjwhite.datastore.jdo.repository.JDORepository;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.model.message.QMessage;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class MessageRepository {
  protected final JDORepository repository;

  public Stream<Message> findWithinThePastHour() {
    final QMessage qMessage = QMessage.candidate();
    final LocalDateTime anHourAgo = LocalDateTime.now().minusHours(1);

    return repository
        .getTypedQuery(Message.class)
        .filter(qMessage.dateCreated.gteq(anHourAgo))
        .executeList()
        .stream();
  }

  public Stream<Message> findUnprocessed() {
    final QMessage qMessage = QMessage.candidate();

    return repository
        .getTypedQuery(Message.class)
        .filter(qMessage.processed.eq(false))
        .executeList()
        .stream();
  }
}
