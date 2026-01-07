package com.walterjwhite.csv.modules.apache.poi;

import com.walterjwhite.csv.modules.apache.poi.writer.ApachePOIExcelWriter;
import java.io.File;
import org.junit.jupiter.api.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestWriteExcel {
  @BeforeAll
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
