package com.walterjwhite.index.api.model.query.predicate;

import com.walterjwhite.index.api.model.query.Conjunction;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@NoArgsConstructor
public class GroupPredicate extends Predicate {

  protected Conjunction conjunction = Conjunction.And;

  protected List<Predicate> childrenPredicates = new ArrayList<>();

  public GroupPredicate(boolean invert, Conjunction conjunction) {
    super(invert);

    this.conjunction = conjunction;
  }
}
