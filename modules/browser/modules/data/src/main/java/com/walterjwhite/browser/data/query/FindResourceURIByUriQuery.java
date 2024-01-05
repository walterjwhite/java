package com.walterjwhite.browser.data.query;

import com.walterjwhite.browser.data.ResourceURI;
import com.walterjwhite.datastore.query.AbstractSingularQuery;
import lombok.Getter;

@Getter
public class FindResourceURIByUriQuery extends AbstractSingularQuery<ResourceURI> {
  protected final String uri;

  public FindResourceURIByUriQuery(final String uri) {
    super(ResourceURI.class, false);
    this.uri = uri;
  }

  //    final CriteriaQueryConfiguration<ResourceURI> resourceURICriteriaQueryConfiguration =
  //        getCriteriaQuery();
  //
  //    Predicate condition =
  //        criteriaBuilder.equal(
  //            resourceURICriteriaQueryConfiguration.getRoot().get(ResourceURI_.name), uri);
  //    resourceURICriteriaQueryConfiguration.getCriteriaQuery().where(condition);
  //
  //    try {
  //      return entityManager
  //          .createQuery(resourceURICriteriaQueryConfiguration.getCriteriaQuery())
  //          .getSingleResult();
  //    } catch (NoResultException e) {
  //      //      LOGGER.info("tx active:" + entityManager.getTransaction().isActive());
  //
  //      return (create(new ResourceURI(uri)));
  //    }
}
