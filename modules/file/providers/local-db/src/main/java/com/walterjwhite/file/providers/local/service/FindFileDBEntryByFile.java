package com.walterjwhite.file.providers.local.service;

import com.walterjwhite.datastore.query.AbstractQuery;
import com.walterjwhite.file.api.model.File;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class FindFileDBEntryByFile extends AbstractQuery<FileDBEntry, FileDBEntry> {
  protected final File file;

  // instantiate the FileDBEntry
  public FindFileDBEntryByFile(File file) {
    super(0, 1, FileDBEntry.class, FileDBEntry.class, false);
    this.file = file;
  }

  //    Predicate condition =
  //            criteriaBuilder.equal(jobCriteriaQueryConfiguration.getRoot().get(File_.id), file);
  //    jobCriteriaQueryConfiguration.getCriteriaQuery().where(condition);
}
