package com.walterjwhite.index.api.model.index;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
public class IndexRecord {


  protected Index index;


  protected String entityId;

  @EqualsAndHashCode.Exclude protected double score;
}
