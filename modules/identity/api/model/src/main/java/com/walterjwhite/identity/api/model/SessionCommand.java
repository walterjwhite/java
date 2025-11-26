package com.walterjwhite.identity.api.model;

import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class SessionCommand /*extends AbstractUUIDEntity*/ {

  protected LocalDateTime commandDateTime;


  protected String nextCommandToken;

  protected SessionCommand previous;

  protected SessionCommand next;
}
