package com.walterjwhite.index.service;

import com.walterjwhite.index.api.enumeration.IndexAction;
import com.walterjwhite.index.api.model.index.IndexSession;
import com.walterjwhite.index.api.service.IndexBulkService;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.queue.api.service.ForkJoinWork;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractIndexBulkService<RequestType>
    implements IndexBulkService<RequestType>, AutoCloseable {

  protected final int recordsPerBatch;
  protected final int timePerBatch;
  protected final IndexService indexService;
  protected transient IndexSession<RequestType> indexSession;

  protected final ForkJoinWork forkJoinWork = new ForkJoinWork();

  public void add(final RequestType request) throws Exception {
    setupIndexSession();

    indexSession.getRequests().add(request);

    flushSession();
  }

  public List<RequestType> get() {
    return indexSession.getRequests();
  }

  protected void setupIndexSession() {
    if (indexSession == null) {
      indexSession = new IndexSession();
      indexSession.setEndDateTime(
          indexSession.getStartDateTime().plus(Duration.ofMillis(timePerBatch)));
    }
  }

  protected void flushSession() throws Exception {
    if (isFlush()) {
      internalFlush();
    }
  }

  protected boolean isFlush() {
    if (indexSession.getRequests().size() >= recordsPerBatch) {
      return true;
    }

    return LocalDateTime.now().isAfter(indexSession.getEndDateTime());
  }

  /**
   * To only be called once at the end of processing all records to ensure they are all written out
   *
   * @throws Exception
   */
  @Override
  public void flush() throws Exception {
    if (isFlushable()) {
      internalFlush();

      forkJoinWork.waitForAll(1, TimeUnit.SECONDS);
    }
  }

  protected boolean isFlushable() {
    return indexSession != null
        && indexSession.getRequests() != null
        && !indexSession.getRequests().isEmpty();
  }

  protected void internalFlush() throws Exception {
    // add the session for auditing purposes
    indexSession
        .getRequests()
        .add(
            (RequestType)
                indexService.buildRequest(
                    IndexAction.Index,
                    indexService.buildIndexableRecord(IndexAction.Index, indexSession)));

    forkJoinWork.submit(
        () -> {
          try {
            doFlush();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
  }

  protected abstract void doFlush() throws IOException;

  @Override
  public void close() {
    // cancel all the clients running in separate threads
    forkJoinWork.close();

    // ensure content is written out
    try {
      flush();
    } catch (Exception e) {
      LOGGER.warn("error while flushing", e);
    }
  }
}
