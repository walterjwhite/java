package com.walterjwhite.print.providers.java;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.print.impl.AbstractPrinterService;
import com.walterjwhite.print.model.PrintJob;
import com.walterjwhite.print.model.PrintRequest;
import com.walterjwhite.print.model.Printer;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.NoOperation;
import java.awt.print.Pageable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.printing.PDFPageable;

public class NativePrinterService extends AbstractPrinterService {
  // protected final PrintJobRepository printJobRepository;

  @Inject
  public NativePrinterService(
      FileStorageService fileStorageService,
      @Property(NoOperation.class) boolean isNooperation,
      Provider<Repository> repositoryProvider) {
    super(isNooperation, fileStorageService, repositoryProvider);
  }

  @Override
  protected void doPrint(PrintJob printJob) throws Exception {
    // printJobRepository.save(printJob);

    doPrint(printJob.getPrinter(), getPDFPageable(printJob.getPrintRequest()));
    // getPrinterService(printer).print(printer, );
  }

  protected PDFPageable getPDFPageable(PrintRequest printRequest) throws Exception {
    if (printRequest.getPassword() != null && !printRequest.getPassword().isEmpty()) {
      return (new PDFPageable(
          Loader.loadPDF(
              new java.io.File(printRequest.getFile().getSource()), printRequest.getPassword())));
    }

    return (new PDFPageable(Loader.loadPDF(new java.io.File(printRequest.getFile().getSource()))));
  }

  protected void doPrint(Printer printer, final Pageable pageable) throws PrinterException {
    java.awt.print.PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintService(getPrinterService(printer));
    job.setPageable(pageable);

    job.print();
  }

  protected javax.print.PrintService getPrinterService(final Printer printer) {
    DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
    PrintRequestAttributeSet printRequestAttributes = new HashPrintRequestAttributeSet();
    printRequestAttributes.add(Sides.DUPLEX);

    return selectPrintService(printer, getPrintServices(flavor, printRequestAttributes));
  }

  protected PrintService[] getPrintServices(
      DocFlavor flavor, PrintRequestAttributeSet printRequestAttributes) {
    final PrintService[] printServices =
        PrintServiceLookup.lookupPrintServices(flavor, printRequestAttributes);
    if (printServices.length == 0) {
      throw new NoPrintersFoundException(
          "No Printer found for " + flavor + " and " + printRequestAttributes);
    }

    return printServices;
  }

  protected PrintService selectPrintService(Printer printer, PrintService[] printServices) {
    if (printer.getName() == null) return printServices[0];

    for (javax.print.PrintService printService : printServices) {
      if (printService.getName().equals(printer.getName())) {
        return printService;
      }
    }

    throw new NoPrinterSelectedException("Printer not found:" + printer.getName());
  }
}
