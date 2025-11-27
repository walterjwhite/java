package com.walterjwhite.index.api.model.index;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class IndexableRecord {
  protected Index index;

  @ToString.Exclude protected transient Object object;
  @ToString.Exclude protected transient byte[] data;
}
