package com.walterjwhite.csv.modules.apache.poi;

import com.walterjwhite.csv.modules.apache.poi.writer.ApachePOIExcelWriter;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWriteExcel {
  @Before
  public void onBefore() {}

  @After
  public void onAfter() {}

  @Test
  public void testWrite() throws Exception {
    final ApachePOIExcelWriter excelWriter =
        new ApachePOIExcelWriter(
            new File("/tmp/excel-write.xslx"), "default", "header1", "header2");
    excelWriter.writeRecord(new String[] {"a", "b", "c"});
    excelWriter.writeRecord(new String[] {"red", "orange", "yellow"});
    excelWriter.writeRecord(new String[] {"up", "down", "left"});

    excelWriter.close();
  }
}
