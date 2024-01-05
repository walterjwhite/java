package com.walterjwhite.remote.impl.query;

import com.walterjwhite.datastore.query.AbstractQuery;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.concurrent.TimeUnit;
import lombok.Getter;

@Getter
public class FindMessagesWithinTimeQueryConfiguration extends AbstractQuery<Message, Message> {
  protected final int amount;
  protected final TimeUnit timeUnit;

  public FindMessagesWithinTimeQueryConfiguration(
      final int offset, final int records, int amount, TimeUnit timeUnit) {
    super(offset, records, Message.class, Message.class, false);
    this.amount = amount;
    this.timeUnit = timeUnit;
  }
}
