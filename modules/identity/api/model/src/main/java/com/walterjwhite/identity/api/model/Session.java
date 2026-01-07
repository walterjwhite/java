package com.walterjwhite.identity.api.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class Session /*extends AbstractUUIDEntity*/ {

  protected LocalDateTime startDateTime;

  protected List<SessionCommand> sessionCommands;
}
