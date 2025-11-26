package com.walterjwhite.index.api.model.index;

import com.walterjwhite.index.api.enumeration.IndexAction;
import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
public class IndexActivity<ResponseType> {
  protected Index index;


  protected IndexAction indexAction;

  protected Serializable indexId;

  @EqualsAndHashCode.Exclude protected ResponseType indexData;
}
