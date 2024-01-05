package com.walterjwhite.download.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Download extends AbstractEntity {
  protected String uri;
  @EqualsAndHashCode.Exclude protected String signature;
  @EqualsAndHashCode.Exclude protected String filename;

  public Download(String uri, String signature) {
    this(uri, signature, getFilename(uri));
  }

  public Download(String uri) {
    this(uri, null);
  }

  public static final String getFilename(final String uri) {
    final int index = uri.lastIndexOf("/");
    return uri.substring(index + 1);
  }
}
