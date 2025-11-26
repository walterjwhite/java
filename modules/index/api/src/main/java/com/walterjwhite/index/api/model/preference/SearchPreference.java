package com.walterjwhite.index.api.model.preference;

import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class SearchPreference {
  protected int resultsPerPage = 100;

}
