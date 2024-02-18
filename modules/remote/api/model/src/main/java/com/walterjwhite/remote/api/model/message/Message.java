package com.walterjwhite.remote.api.model.message;


import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.serialization.api.annotation.PrivateField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.listener.StoreCallback;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class Message extends AbstractEntity implements StoreCallback {
  
  protected Client sender;

  protected LocalDateTime dateCreated = LocalDateTime.now();

  @EqualsAndHashCode.Exclude @PrivateField 
  protected Set<Client> recipients = new HashSet<>();

  @EqualsAndHashCode.Exclude protected LocalDateTime dateSent;
  @EqualsAndHashCode.Exclude protected boolean processed;

  @EqualsAndHashCode.Exclude protected int timeToLive;

  @EqualsAndHashCode.Exclude
  
  protected String token;

  @Override
  public void jdoPreStore() {
    this.dateSent = LocalDateTime.now();
  }
}
