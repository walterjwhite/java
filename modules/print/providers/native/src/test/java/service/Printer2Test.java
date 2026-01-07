package service;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

@Slf4j
public class Printer2Test {

  public static void main(final String[] arguments) throws PrinterException {
    PDDocument doc = null; // PDDocument.load(new File("label.pdf"));
    PDFPageable pdfPageable = new PDFPageable(doc);

    PrintService selectedPrintService = null;
    for (final PrintService printService : PrinterJob.lookupPrintServices()) {
      LOGGER.info("printer service:" + printService);
      if (printService.getName().contains("Default")) {
        selectedPrintService = printService;
        break;
      }
    }

    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintService(selectedPrintService);
    LOGGER.info("selectedPrintService:" + selectedPrintService);
    job.setPageable(new PDFPageable(doc));


  }
}
