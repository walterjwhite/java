package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractUUIDEntity;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class IndexSession<RequestType> extends AbstractUUIDEntity {
  protected transient LocalDateTime startDateTime = LocalDateTime.now();
  protected long start = startDateTime.toEpochSecond(ZoneOffset.UTC);

  protected transient List<RequestType> requests = new ArrayList<>();

  // delete:<ID>
  // index:<ID>
  protected List<IndexSessionRecordActivity> activities = new ArrayList<>();

  protected transient LocalDateTime endDateTime;
  protected long end;
}
