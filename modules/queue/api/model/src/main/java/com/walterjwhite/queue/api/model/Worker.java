package com.walterjwhite.queue.api.model;

import com.walterjwhite.infrastructure.inject.core.model.ApplicationSession;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@PersistenceCapable
@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class Worker /*extends AbstractUUIDEntity*/ {

  protected ApplicationSession applicationSession;
}
