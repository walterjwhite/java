package com.walterjwhite.index.modules.elasticsearch.enumeration;

import com.walterjwhite.index.api.enumeration.IndexAction;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
// import org.elasticsearch.common.xcontent.XContentType;

@Getter
@RequiredArgsConstructor
public enum ElasticSearchIndexAction {
  Index(IndexAction.Index) {
    public IndexRequest buildRequest(final IndexableRecord indexableRecord) {
      // return new IndexRequest(indexableRecord.getIndex().getName())
      //     .id(indexableRecord.getEntityReference().getEntityId().toString())
      //     .source(indexableRecord.getData(), XContentType.YAML);
      throw new UnsupportedOperationException("Migrate to Java Client");
    }
  },
  Update(IndexAction.Update) {
    public UpdateRequest buildRequest(final IndexableRecord indexableRecord) {
      // return new UpdateRequest(
      //         indexableRecord.getIndex().getName(),
      //         indexableRecord.getEntityReference().getEntityId().toString())
      //     .doc(indexableRecord.getData(), XContentType.YAML);
      throw new UnsupportedOperationException("Migrate to Java Client");
    }
  },
  Delete(IndexAction.Delete) {
    public DeleteRequest buildRequest(final IndexableRecord indexableRecord) {
      return new DeleteRequest(
          indexableRecord.getIndex().getName(),
          indexableRecord.getEntityReference().getEntityId().toString());
    }
  };

  protected final IndexAction indexAction;

  public abstract <RequestType extends DocWriteRequest> RequestType buildRequest(
      final IndexableRecord indexableRecord);

  public static ElasticSearchIndexAction get(final IndexAction indexAction) {
    return Arrays.stream(values())
        .filter(
            elasticSearchIndexAction -> elasticSearchIndexAction.indexAction.equals(indexAction))
        .findFirst()
        .get();
  }
}
