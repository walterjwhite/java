package com.walterjwhite.index.modules.jpa.enumeration;

import com.walterjwhite.datastore.api.model.entity.Entity;
import com.walterjwhite.index.api.annotation.NotIndexed;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.api.service.IndexService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum IndexAction {
  Index(
  /*JPAActionType.Persist*/ ) {
    protected void doRun(IndexService indexService, IndexableRecord indexableRecord)
        throws Exception {
      indexService.index(indexableRecord.getObject());
    }
  },
  Update(
  /*JPAActionType.Update*/ ) {
    protected void doRun(IndexService indexService, IndexableRecord indexableRecord)
        throws Exception {
      indexService.update(indexableRecord.getObject());
    }
  },
  Delete(
  /*JPAActionType.Remove*/ ) {
    protected void doRun(IndexService indexService, IndexableRecord indexableRecord)
        throws Exception {
      indexService.delete(indexableRecord.getObject());
    }
  };

  // private final JPAActionType jpaActionType;

  public void run(final IndexService indexService, IndexableRecord indexableRecord)
      throws Exception {
    if (isIndexed(indexableRecord.getEntityReference().getEntityType()))
      doRun(indexService, indexableRecord);
  }

  protected boolean isIndexed(final Entity entity) {
    return !entity.getClass().isAnnotationPresent(NotIndexed.class);
  }

  protected abstract void doRun(IndexService indexService, IndexableRecord indexableRecord)
      throws Exception;

  public static IndexAction getIndexAction(/*JPAActionType jpaActionType*/ ) {
    /*
        for (IndexAction indexAction : values()) {
          if (indexAction.jpaActionType.equals(jpaActionType)) return indexAction;
        }
    */
    throw new IllegalArgumentException(/*jpaActionType + */ " is unsupported");
  }
}
