package com.walterjwhite.index.api.model.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexRecord;
import com.walterjwhite.index.api.model.query.predicate.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@NoArgsConstructor
public class SearchQuery extends AbstractEntity {
  @EqualsAndHashCode.Exclude protected Index index;

  @EqualsAndHashCode.Exclude
  protected List<SearchQueryEntityType> searchQueryEntityTypes = new ArrayList<>();

  @EqualsAndHashCode.Exclude protected Predicate predicate;

  protected LocalDateTime dateTime;

  @EqualsAndHashCode.Exclude protected int resultOffset = 0;

  @EqualsAndHashCode.Exclude protected int resultSize = -1;

  @EqualsAndHashCode.Exclude protected long total;

  @EqualsAndHashCode.Exclude protected List<IndexRecord> indexRecords = new ArrayList<>();

  @EqualsAndHashCode.Exclude protected long executionTime;

  public SearchQuery(Index index, EntityType entityType, Predicate predicate) {

    this.index = index;
    this.searchQueryEntityTypes.add(new SearchQueryEntityType(entityType, this));
    this.predicate = predicate;
    this.dateTime = LocalDateTime.now();
  }
}
