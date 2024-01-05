package service;

import java.io.File;
import java.io.IOException;

public class PDFViewer {
  public static void main(final String[] arguments) throws IOException, InterruptedException {
    final File pdfFile = new File("label.pdf");

    /*
    PDDocument document = PDDocument.load(pdfFile);
    //PDFRenderer pdfRenderer = new PDFRenderer(document);

    List<PDPage> allPages = document.getPages().getCount();

    JFrame testFrame = new JFrame();
    testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    PDFPagePanel pdfPanel = new PDFPagePanel();
    pdfPanel.setPage(testPage);
    //testFrame.add(pdfPanel);
    //testFrame.add(document.getPage(0).);
    testFrame.setBounds(40, 40, pdfPanel.getWidth(), pdfPanel.getHeight());
    testFrame.setVisible(true);

    inputPDF.close();
    PDPage testPage = (PDPage)allPages.get(0);
    */

    // simply launch and wait for us to close the viewer
    Process process =
        Runtime.getRuntime().exec(new String[] {"AcroRd32.exe", pdfFile.getAbsolutePath()});
    process.waitFor();
  }
}
