package com.walterjwhite.index.api.model.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
public class SearchQueryEntityType extends AbstractEntity {

  protected EntityType entityType;

  protected SearchQuery searchQuery;
}
