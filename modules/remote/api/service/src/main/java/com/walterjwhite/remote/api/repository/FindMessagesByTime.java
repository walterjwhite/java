package com.walterjwhite.remote.api.repository;

import com.walterjwhite.datastore.query.AbstractQuery;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.concurrent.TimeUnit;

public class FindMessagesByTime extends AbstractQuery<Message, Message> {
  protected final long value;
  protected final TimeUnit units;

  public FindMessagesByTime(int offset, int recordCount, long value, TimeUnit units) {
    super(offset, recordCount, Message.class, Message.class, false);
    this.value = value;
    this.units = units;

    // Message_.DATE_CREATED
  }
}
