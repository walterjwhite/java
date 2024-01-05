package com.walterjwhite.remote.impl.query;

import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.criteria.query.JpaCriteriaQueryBuilder;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.model.message.Message_;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class FindMessagesWithinTimeQueryBuilder
    extends JpaCriteriaQueryBuilder<Message, Message, FindMessagesWithinTimeQueryConfiguration> {

  protected Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<Message, Message> criteriaQueryConfiguration,
      FindMessagesWithinTimeQueryConfiguration findMessagesWithinTimeQueryConfiguration) {
    return criteriaBuilder.greaterThanOrEqualTo(
        criteriaQueryConfiguration.getRoot().get(Message_.dateSent),
        getStartTime(
            findMessagesWithinTimeQueryConfiguration.getAmount(),
            findMessagesWithinTimeQueryConfiguration.getTimeUnit()));
  }

  private static LocalDateTime getStartTime(int amount, TimeUnit timeUnit) {
    if (TimeUnit.HOURS.equals(timeUnit)) return LocalDateTime.now().minusHours(amount);
    if (TimeUnit.MINUTES.equals(timeUnit)) return LocalDateTime.now().minusMinutes(amount);

    throw new UnsupportedOperationException("unknown timeUnit:" + timeUnit);
  }
}
