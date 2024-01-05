package com.walterjwhite.browser.data;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class BrowserSessionResourceURI extends AbstractEntity {

  protected ResourceURI resourceURI;

  protected int index = 0;

  protected BrowserSession browserSession;

  protected LocalDateTime dateVisited = LocalDateTime.now();

  protected BrowserSessionResourceURI parent;

  @ToString.Exclude @EqualsAndHashCode.Exclude protected Set<BrowserSessionResourceURI> children;

  public BrowserSessionResourceURI(
      ResourceURI resourceURI, BrowserSession browserSession, BrowserSessionResourceURI parent) {
    this();

    this.resourceURI = resourceURI;
    this.browserSession = browserSession;
    this.parent = parent;
  }

  public BrowserSessionResourceURI(
      ResourceURI resourceURI,
      BrowserSession browserSession,
      BrowserSessionResourceURI parent,
      int index) {
    this();

    this.resourceURI = resourceURI;
    this.browserSession = browserSession;
    this.parent = parent;
    this.index = index;
  }

  public BrowserSessionResourceURI() {

    children = new HashSet<>();
  }
}
