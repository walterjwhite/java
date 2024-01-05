package com.walterjwhite.browser.data;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class BrowserSession extends AbstractEntity {
  @EqualsAndHashCode.Exclude protected LocalDateTime startDateTime = LocalDateTime.now();

  // how long should we wait in between actions (this will help us look real to the website)
  protected Duration maximumDelayBetweenActions;
  /*


  protected Node node;
  */

  protected String uuid = UUID.randomUUID().toString();

  @ToString.Exclude @EqualsAndHashCode.Exclude
  protected List<BrowserSessionResourceURI> browserSessionResourceURIs = new ArrayList<>();

  public BrowserSession(List<BrowserSessionResourceURI> browserSessionResourceURIs) {
    this();

    if (browserSessionResourceURIs != null && !browserSessionResourceURIs.isEmpty())
      this.browserSessionResourceURIs.addAll(browserSessionResourceURIs);
  }
}
