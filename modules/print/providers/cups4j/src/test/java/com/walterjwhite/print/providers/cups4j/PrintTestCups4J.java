package com.walterjwhite.print.providers.cups4j;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.cups4j.CupsClient;
import org.cups4j.PrintJob;

public class PrintTestCups4J {
  public static void main(final String[] arguments) throws Exception {
    // @TODO: inject these parameters / let the user select them
    CupsClient cupsClient = new CupsClient("localhost", 631);

    final byte[] data = FileUtils.readFileToByteArray(new File("/tmp/print-queuedJob-test"));
    cupsClient.getDefaultPrinter().print(new PrintJob.Builder(data).build());
  }
}
