package com.walterjwhite.csv.modules.apache.poi.reader;

import com.walterjwhite.csv.api.service.reader.ExcelReader;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ApachePOIExcelReader implements ExcelReader {
  protected final Workbook workbook;
  protected final ApachePOIExcelRecordIteratorWrapper wrapper;

  protected int currentRow = 0;

  public ApachePOIExcelReader(final File file, final String tabName) throws IOException {

    workbook = WorkbookFactory.create(file);
    wrapper = new ApachePOIExcelRecordIteratorWrapper(workbook.getSheet(tabName));

    //    // Write the output to a file
    //    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
    //    wb.write(fileOut);
    //    fileOut.close();
  }

  @Override
  public Iterator<String[]> iterator() {
    return wrapper;
  }

  @Override
  public String[] getHeaders() {
    //    return sheet.getTopRow();
    return null;
  }

  @Override
  public void close() throws Exception {
    workbook.close();
  }
}
