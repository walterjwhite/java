package com.walterjwhite.data.pipe.impl.periodic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PeriodicRecord<SourceRecordType extends Serializable> implements Serializable {
  protected final LocalDateTime startDateTime;
  protected final List<SourceRecordType> result = new ArrayList<>();
  protected final LocalDateTime endDateTime;

  public PeriodicRecord(LocalDateTime startDateTime, List<SourceRecordType> result) {

    this.startDateTime = startDateTime;
    if (result != null && !result.isEmpty()) this.result.addAll(result);

    this.endDateTime = LocalDateTime.now();
  }

  public LocalDateTime getStartDateTime() {
    return startDateTime;
  }

  public List<SourceRecordType> getResult() {
    return result;
  }

  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }
}
