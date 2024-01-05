package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.serialization.api.annotation.PrivateField;
import java.time.LocalDateTime;
import java.util.*;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.listener.StoreCallback;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
// @MappedSuperclass
@PersistenceCapable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends AbstractEntity implements StoreCallback {
  /** populated when the message is sent * */
  protected Client sender;

  protected LocalDateTime dateCreated = LocalDateTime.now();

  @EqualsAndHashCode.Exclude @PrivateField /*(cascade = CascadeType.ALL)*/
  protected Set<Client> recipients = new HashSet<>();

  @EqualsAndHashCode.Exclude protected LocalDateTime dateSent;

  @EqualsAndHashCode.Exclude protected int timeToLive;

  @EqualsAndHashCode.Exclude
  /** Compute a hash of the message: sender UUID, time, IP, etc. */
  protected String token;

  protected Message(Set<Client> recipients, int timeToLive) {
    this(timeToLive);

    this.recipients.addAll(recipients);
  }

  protected Message(Client recipient, int timeToLive) {
    this(timeToLive);

    this.recipients.add(recipient);
  }

  protected Message(int timeToLive) {
    this();

    this.timeToLive = timeToLive;
  }

  @Override
  public void jdoPreStore() {
    this.dateSent = LocalDateTime.now();
  }
}
