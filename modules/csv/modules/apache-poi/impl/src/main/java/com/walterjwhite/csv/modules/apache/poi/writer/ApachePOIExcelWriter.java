package com.walterjwhite.csv.modules.apache.poi.writer;

import com.walterjwhite.csv.api.service.writer.ExcelWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIExcelWriter implements ExcelWriter {
  protected final FileOutputStream fileOutputStream;
  protected final Workbook workbook;
  protected final Sheet sheet;
  protected int index = 0;

  public ApachePOIExcelWriter(final File file, final String tabName, final String... headers)
      throws IOException {

    fileOutputStream = new FileOutputStream(file);

    //    workbook = WorkbookFactory.create(file);
    workbook = new XSSFWorkbook();
    sheet = workbook.createSheet(tabName);

    writeRecord(headers);
  }

  @Override
  public void writeRecord(String[] record) {
    Row row = sheet.createRow(index++);
    for (int cellIndex = 0; cellIndex < record.length; cellIndex++) {
      Cell cell = row.createCell(cellIndex);
      cell.setCellValue(record[cellIndex]);
    }
  }

  @Override
  public void close() throws Exception {
    workbook.write(fileOutputStream);
    fileOutputStream.flush();
    fileOutputStream.close();

    workbook.close();
  }
}
