package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.query.SearchQuery;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
public class IndexRecord extends AbstractEntity {

  protected SearchQuery searchQuery;

  protected Index index;

  protected EntityType entityType;

  protected String entityId;

  @EqualsAndHashCode.Exclude protected double score;
}
