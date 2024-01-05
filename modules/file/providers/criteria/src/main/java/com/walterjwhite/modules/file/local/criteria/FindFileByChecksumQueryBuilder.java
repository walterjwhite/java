package com.walterjwhite.modules.file.local.criteria;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.api.model.File_;
import com.walterjwhite.infrastructure.datastore.modules.criteria.CriteriaQueryConfiguration;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;

public class FindFileByChecksumQueryBuilder {
  private static Predicate buildPredicate(
      CriteriaBuilder criteriaBuilder,
      CriteriaQueryConfiguration<File, File> criteriaQueryConfiguration,
      String checksum) {
    return criteriaBuilder.equal(
        criteriaQueryConfiguration.getRoot().get(File_.checksum), checksum);
  }
}
