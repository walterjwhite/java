package com.walterjwhite.index.api.model.index;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@NoArgsConstructor
public class Index {


  @EqualsAndHashCode.Exclude protected String mapping;

}
