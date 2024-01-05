package com.walterjwhite.index.modules.jpa.callable;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.index.modules.jpa.property.IndexTimeoutUnits;
import com.walterjwhite.index.modules.jpa.property.IndexTimeoutValue;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.queue.event.annotation.SubscribeTo;
import java.time.temporal.ChronoUnit;
import jakarta.inject.Inject;

@SubscribeTo(eventClass = AbstractEntity.class /*,
  jpaAction = {JPAActionType.Update}*/)
public class IndexEventProcessorUpdateRunnable extends AbstractIndexEventProcessorRunnable {
  @Inject
  public IndexEventProcessorUpdateRunnable(
      IndexService indexService,
      @Property(IndexTimeoutUnits.class) ChronoUnit timeoutUnits,
      @Property(IndexTimeoutValue.class) long timeoutValue) {
    super(indexService);
  }

  protected IndexActivity doIndex() throws Exception {
    return (indexService.update((AbstractEntity) indexableRecord.getObject()));
  }
}
