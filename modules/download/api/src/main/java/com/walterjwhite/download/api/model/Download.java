package com.walterjwhite.download.api.model;


import javax.jdo.annotations.PersistenceCapable;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Download extends AbstractEntity {
  protected String uri;
  @EqualsAndHashCode.Exclude protected String signature;
  @EqualsAndHashCode.Exclude protected String filename;
}
