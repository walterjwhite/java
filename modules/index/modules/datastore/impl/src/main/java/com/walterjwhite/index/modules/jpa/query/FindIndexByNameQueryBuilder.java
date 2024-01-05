/*
TODO: pull this out into a datastore implementation (JDO/JPA)

package com.walterjwhite.index.modules.jpa.query;
import com.walterjwhite.datastore.api.annotation.Supports;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.Index_;
import com.walterjwhite.index.query.FindIndexByNameQuery;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.infrastructure.datastore.modules.criteria.query.JpaCriteriaQueryBuilder;
@Supports(com.walterjwhite.index.api.service.query.FindIndexByNameQuery.class)
public class FindIndexByNameQueryBuilder
   extends JpaCriteriaQueryBuilder<Index, Index, FindIndexByNameQuery> {
 @Override
 protected Predicate buildPredicate(
     CriteriaBuilder criteriaBuilder,
     CriteriaQueryConfiguration<Index, Index> criteriaQueryConfiguration,
     FindIndexByNameQuery findIndexByNameQuery) {
   return criteriaBuilder.equal(
       criteriaQueryConfiguration.getRoot().get(Index_.name), findIndexByNameQuery.getName());
 }
}
*/
