package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class EmailSendRequest extends AbstractEntity {
  protected Email email;

  @EqualsAndHashCode.Exclude protected LocalDateTime updatedDate;
  @EqualsAndHashCode.Exclude protected LocalDateTime sentDate;

  @EqualsAndHashCode.Exclude protected long timeout;

  @EqualsAndHashCode.Exclude protected int retryAttempts;

  @EqualsAndHashCode.Exclude protected boolean synchronous = true;

  public EmailSendRequest withEmail(Email email) {
    this.email = email;
    return this;
  }

  public EmailSendRequest withTimeout(long timeout) {
    this.timeout = timeout;
    return this;
  }
}
