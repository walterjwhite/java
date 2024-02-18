package service;

import java.io.File;
import java.io.IOException;

public class PDFViewer {
  public static void main(final String[] arguments) throws IOException, InterruptedException {
    final File pdfFile = new File("label.pdf");

    


    Process process =
        Runtime.getRuntime().exec(new String[] {"AcroRd32.exe", pdfFile.getAbsolutePath()});
    process.waitFor();
  }
}
