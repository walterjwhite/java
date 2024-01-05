package com.walterjwhite.index.api.model.query;

public enum FieldType {
  Boolean(MatchType.Equals),
  Number(
      MatchType.Equals,
      MatchType.Range,
      MatchType.GreaterThan,
      MatchType.GreaterThanOrEquals,
      MatchType.LessThan,
      MatchType.LessThanOrEquals),
  Text(MatchType.Equals, MatchType.Like, MatchType.Regex);

  private final MatchType[] supportedMatchTypes;

  FieldType(MatchType... supportedMatchTypes) {
    this.supportedMatchTypes = supportedMatchTypes;
  }
}
