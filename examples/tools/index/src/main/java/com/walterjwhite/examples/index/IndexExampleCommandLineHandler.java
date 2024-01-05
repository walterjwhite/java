package com.walterjwhite.examples.index;

import com.walterjwhite.index.api.service.IndexBulkService;
import com.walterjwhite.index.api.service.IndexMaintenanceService;
import com.walterjwhite.index.api.service.IndexSearchService;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import java.io.IOException;
import java.time.LocalDateTime;
import jakarta.inject.Inject;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
// import org.elasticsearch.ElasticsearchStatusException;

@Slf4j
public class IndexExampleCommandLineHandler implements CommandLineHandler {

  //  protected final Repository repository;
  protected final IndexService indexService;
  protected final IndexMaintenanceService indexMaintenanceService;
  protected final IndexSearchService indexSearchService;
  protected final IndexBulkService indexBulkService;

  @Inject
  public IndexExampleCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      //      Repository repository,
      IndexService indexService,
      IndexMaintenanceService indexMaintenanceService,
      IndexSearchService indexSearchService,
      IndexBulkService indexBulkService) {
    //    super(shutdownTimeoutInSeconds);
    //    this.repository = repository;
    this.indexService = indexService;
    this.indexMaintenanceService = indexMaintenanceService;
    this.indexSearchService = indexSearchService;
    this.indexBulkService = indexBulkService;
  }

  @Transactional
  public IndexExampleEntity createEntity() {
    IndexExampleEntity indexExampleEntity = new IndexExampleEntity();
    indexExampleEntity.setId(1);
    indexExampleEntity.setDateTime(LocalDateTime.now());
    indexExampleEntity.setName("some name");
    indexExampleEntity.setFavoriteNumber(527);

    //    repository.create(indexExampleEntity);

    return indexExampleEntity;
  }

  protected void index(IndexExampleEntity indexExampleEntity) throws Exception {
    indexMaintenanceService.create(indexExampleEntity.getClass());

    // future
    indexService.index(indexExampleEntity);

    indexBulkService.add(indexExampleEntity);
    indexBulkService.flush();
  }

  protected void delete(IndexExampleEntity indexExampleEntity) throws Exception {
    indexService.delete(indexExampleEntity);
    indexMaintenanceService.delete(indexExampleEntity.getClass());
  }

  //  public void search() throws Exception {
  //    // SearchQuery searchQuery = new SearchQuery(new Index("default"), repository.query(new
  //    // FindEntityTypeByNameQueryConfiguration(IndexExampleEntity.class.getSimpleName())), null);
  //    SearchQuery searchQuery =
  //        new SearchQuery(
  //            new Index("default"),
  //            repository.query(
  //                new FindEntityTypeByNameQuery(IndexExampleEntity.class.getSimpleName())),
  //            new AttributePredicate(FieldType.Text, MatchType.Like, "name", "some"));
  //    indexSearchService.search(searchQuery);
  //
  //    searchQuery
  //        .getIndexRecords()
  //  }

  @Override
  public void run(String... arguments) throws Exception {
    // Log.warn(indexMaintenanceService.delete(IndexExampleEntity.class), IOException.class);
    try {
      indexMaintenanceService.delete(IndexExampleEntity.class);
    } catch (IOException /* | ElasticsearchStatusException*/ e) {
      LOGGER.warn("Index must not exist:", e);
    }

    // index should be created at this point
    final IndexExampleEntity indexExampleEntity = createEntity();
    index(indexExampleEntity);

    // wait a little bit to let the indexing take place
    //    Thread.sleep(5 * 1000);

    //    search();

    delete(indexExampleEntity);

    // step 2: index a CSV file, illustrate searching across fields ...
  }
}
