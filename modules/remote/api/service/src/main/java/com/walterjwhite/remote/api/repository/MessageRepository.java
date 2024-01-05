package com.walterjwhite.remote.api.repository;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.List;

/** Wrapper around external queue service */
public interface MessageRepository extends Repository {
  List<Message> findWithinThePastHour();
}
