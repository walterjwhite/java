package com.walterjwhite.examples.index;

import com.walterjwhite.index.api.service.IndexBulkService;
import com.walterjwhite.index.api.service.IndexMaintenanceService;
import com.walterjwhite.index.api.service.IndexSearchService;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class IndexExampleCommandLineHandler implements CommandLineHandler {

  protected final IndexService indexService;
  protected final IndexMaintenanceService indexMaintenanceService;
  protected final IndexSearchService indexSearchService;
  protected final IndexBulkService indexBulkService;

  @Inject
  public IndexExampleCommandLineHandler(
      IndexService indexService,
      IndexMaintenanceService indexMaintenanceService,
      IndexSearchService indexSearchService,
      IndexBulkService indexBulkService) {
    this.indexService = indexService;
    this.indexMaintenanceService = indexMaintenanceService;
    this.indexSearchService = indexSearchService;
    this.indexBulkService = indexBulkService;
  }

  @Transactional
  public IndexExampleEntity createEntity() {
    IndexExampleEntity indexExampleEntity = new IndexExampleEntity();
    indexExampleEntity.setDateTime(LocalDateTime.now());
    indexExampleEntity.setName("some name");
    indexExampleEntity.setFavoriteNumber(527);


    return indexExampleEntity;
  }

  protected void index(IndexExampleEntity indexExampleEntity) throws Exception {
    indexMaintenanceService.create(indexExampleEntity.getClass());


    indexBulkService.add(indexExampleEntity);
    indexBulkService.flush();
  }

  protected void delete(IndexExampleEntity indexExampleEntity) throws Exception {
    indexMaintenanceService.delete(indexExampleEntity.getClass());
  }


  @Override
  public void run(String... arguments) throws Exception {
    try {
      indexMaintenanceService.delete(IndexExampleEntity.class);
    } catch (IOException /* | ElasticsearchStatusException*/ e) {
      LOGGER.warn("Index must not exist:", e);
    }

    final IndexExampleEntity indexExampleEntity = createEntity();
    index(indexExampleEntity);



    delete(indexExampleEntity);

  }
}
