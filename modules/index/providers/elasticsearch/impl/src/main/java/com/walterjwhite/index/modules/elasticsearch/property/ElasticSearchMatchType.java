package com.walterjwhite.index.modules.elasticsearch.property;

import com.walterjwhite.index.api.model.query.MatchType;
import com.walterjwhite.index.api.model.query.predicate.AttributePredicate;
import com.walterjwhite.index.api.model.query.predicate.Range;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

@Getter
@RequiredArgsConstructor
public enum ElasticSearchMatchType {
  Equals(MatchType.Equals) {
    public QueryBuilder build(AttributePredicate attributePredicate) {
      return (QueryBuilders.matchQuery(
          attributePredicate.getAttributePath(), attributePredicate.getArgument()));
    }
  },
  Like(MatchType.Like) {
    public QueryBuilder build(AttributePredicate attributePredicate) {
      return (QueryBuilders.fuzzyQuery(
          attributePredicate.getAttributePath(), attributePredicate.getArgument()));
    }
  },
  Regex(MatchType.Regex) {
    public QueryBuilder build(AttributePredicate attributePredicate) {
      return (QueryBuilders.regexpQuery(
          attributePredicate.getAttributePath(), (String) attributePredicate.getArgument()));
    }
  },
  Range(MatchType.Range) {
    public QueryBuilder build(AttributePredicate attributePredicate) {
      return (QueryBuilders.rangeQuery(attributePredicate.getAttributePath())
          .gt(
              ((com.walterjwhite.index.api.model.query.predicate.Range)
                      attributePredicate.getArgument())
                  .getLow())
          .lt(((Range) attributePredicate.getArgument()).getHigh()));
    }
  };

  private final MatchType matchType;

  public abstract QueryBuilder build(AttributePredicate attributePredicate);

  public static ElasticSearchMatchType get(MatchType matchType) {
    // Arrays.stream(values()).findFirst(elasticSearchMatchType -> {})
    for (ElasticSearchMatchType elasticSearchMatchType : values()) {
      if (elasticSearchMatchType.getMatchType().equals(matchType)) return elasticSearchMatchType;
    }

    throw new UnsupportedOperationException("Not yet supported." + matchType);
  }
}
