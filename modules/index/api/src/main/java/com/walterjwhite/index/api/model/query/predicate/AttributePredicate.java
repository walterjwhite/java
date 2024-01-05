package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.index.api.model.query.FieldType;
import com.walterjwhite.index.api.model.query.MatchType;
import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class AttributePredicate extends Predicate {

  protected FieldType fieldType;

  protected MatchType matchType;

  protected String attributePath;

  protected Serializable argument;

  public AttributePredicate(
      boolean invert,
      FieldType fieldType,
      MatchType matchType,
      String attributePath,
      Serializable argument) {
    super(invert);
    this.fieldType = fieldType;
    this.matchType = matchType;
    this.attributePath = attributePath;
    this.argument = argument;
  }

  public AttributePredicate(
      FieldType fieldType, MatchType matchType, String attributePath, Serializable argument) {

    this.fieldType = fieldType;
    this.matchType = matchType;
    this.attributePath = attributePath;
    this.argument = argument;
  }
}
