package com.walterjwhite.index.api.model.preference;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class SearchPreference extends AbstractEntity {
  //  protected User user;
  protected int resultsPerPage = 100;

  //  protected Person person;
}
