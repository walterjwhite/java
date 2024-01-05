package com.walterjwhite.print.impl;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.print.api.service.FindPrinterByLocationAndPrinterType;
import com.walterjwhite.print.api.service.PrinterService;
import com.walterjwhite.print.model.PrintJob;
import com.walterjwhite.print.model.PrintRequest;
import com.walterjwhite.print.model.Printer;
import jakarta.inject.Provider;
import javax.transaction.Transactional;

public abstract class AbstractPrinterService implements PrinterService {
  protected final boolean isNooperation;

  protected final FileStorageService fileStorageService;

  protected final Provider<Repository> repositoryProvider;

  protected AbstractPrinterService(
      boolean isNooperation,
      FileStorageService fileStorageService,
      Provider<Repository> repositoryProvider) {
    this.isNooperation = isNooperation;
    this.fileStorageService = fileStorageService;
    this.repositoryProvider = repositoryProvider;
  }

  @Transactional
  @Override
  public PrintJob print(PrintRequest printRequest) throws Exception {
    final Repository entityRepository = repositoryProvider.get();

    if (printRequest.getId() == null) entityRepository.create(printRequest);

    PrintJob printJob = new PrintJob(getPrinter(printRequest), printRequest);
    entityRepository.create(printJob);

    if (!isNooperation) {
      // ensure we get a copy of the file locally before trying to print
      fileStorageService.get(printRequest.getFile());
      doPrint(printJob);
    }

    return entityRepository.create(
  }

  protected Printer getPrinter(PrintRequest printRequest) {
    if (printRequest.getLocation() == null) {
      return repositoryProvider
          .get()
          .query(new FindPrinterByLocationAndPrinterType(0, 1, printRequest.getLocation(), null));
    }

    throw new IllegalStateException("No printer chosen");
  }

  protected abstract void doPrint(PrintJob printJob) throws Exception;
}
