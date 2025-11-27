package com.walterjwhite.csv.modules.apache.poi.reader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ApachePOIExcelRecordIteratorWrapper implements Iterator<String[]> {
  protected final Sheet sheet;
  protected int index = 1;

  public ApachePOIExcelRecordIteratorWrapper(final Sheet sheet) {

    this.sheet = sheet;
  }

  @Override
  public boolean hasNext() {
    return index < sheet.getLastRowNum();
  }

  @Override
  public String[] next() {
    final Row row = sheet.getRow(index++);
    final Iterator<Cell> cellIterator = row.cellIterator();
    final List<String> cells = new ArrayList<>();
    while (cellIterator.hasNext()) {
      cells.add(cellIterator.next().getStringCellValue());
    }

    return (cells.toArray(new String[cells.size()]));
  }
}
