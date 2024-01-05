package com.walterjwhite.queue.providers.local.service;

import com.walterjwhite.file.modules.watcher.DirectoryWatcherService;
import com.walterjwhite.file.modules.watcher.SimpleDirectoryWatcherService;
import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
import com.walterjwhite.job.external.queue.api.ExternalQueueBridgeService;
import com.walterjwhite.job.external.queue.impl.file.FileInputStreamProducer;
import com.walterjwhite.queue.api.enumeration.QueueType;
import com.walterjwhite.queue.api.model.Queue;
import com.walterjwhite.queue.api.model.QueueMonitor;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/** Watches all configured queues */
@Slf4j
public class AsynchronousLocalQueueReaderService implements StartupAware {
  protected final QueueMonitor queueMonitor;
  protected final Set<DirectoryWatcherService> directoryWatcherServices = new HashSet<>();
  protected final ExternalQueueBridgeService externalQueueBridgeService;

  @Inject
  public AsynchronousLocalQueueReaderService(
      QueueMonitor queueMonitor, ExternalQueueBridgeService externalQueueBridgeService)
      throws IOException {

    this.queueMonitor = queueMonitor;
    this.externalQueueBridgeService = externalQueueBridgeService;

    setupDirectoryWatchers(queueMonitor);
  }

  protected void setupDirectoryWatchers(QueueMonitor queueMonitor) throws IOException {
    for (Queue queue : queueMonitor.getQueues()) {
      directoryWatcherServices.add(setupDirectoryWatcher(queue));
    }

    // also need a generic queue directory so that we can send outgoing messages from the
    // command-line or simply by dropping a file in a directory
    // NOTE: these files must be encrypted and compressed, otherwise, we will fail to read them
    directoryWatcherServices.add(setupDirectoryWatcher(new Queue("outbound", QueueType.Outbound)));
  }

  protected DirectoryWatcherService setupDirectoryWatcher(Queue queue) throws IOException {
    // prepare directories
    for (QueueStatus queueStatus : QueueStatus.values()) {
      LocalQueueUtil.getTargetDirectory(queue, queueStatus).mkdirs();
    }

    DirectoryWatcherService directoryWatcherService = new SimpleDirectoryWatcherService();
    directoryWatcherService.register(
        new DirectoryWatcherService.OnFileChangeListener() {
          @Override
          public void onFileCreate(String filePath) {
            final File source =
                LocalQueueUtil.getTargetFile(
                    LocalQueueUtil.getTargetDirectory(queue, QueueStatus.New), filePath);
            final File target =
                LocalQueueUtil.getTargetFile(
                    LocalQueueUtil.getTargetDirectory(queue, QueueStatus.Processed), filePath);

            try {
              externalQueueBridgeService.read(queue, new FileInputStreamProducer(source, target));
            } catch (Exception e) {
              handleException(e, source, queue, filePath);
            }
          }
        },
        LocalQueueUtil.getTargetDirectory(queue, QueueStatus.New).getAbsolutePath(),
        "*");

    return (directoryWatcherService);
  }

  protected void handleException(
      Exception e, final File source, final Queue queue, final String filePath) {
    LOGGER.warn("exception", e);
    final File target =
        LocalQueueUtil.getTargetFile(
            LocalQueueUtil.getTargetDirectory(queue, QueueStatus.Exception), filePath);

    source.renameTo(target);
  }

  @Override
  public void startup() {
    directoryWatcherServices.stream()
        .forEach(directoryWatcherService -> directoryWatcherService.start());
  }

  @Override
  public void close() {
    directoryWatcherServices.stream()
        .forEach(directoryWatcherService -> directoryWatcherService.stop());
  }
}
