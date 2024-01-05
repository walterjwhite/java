package com.walterjwhite.csv.modules.univocity.reader;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.walterjwhite.csv.api.service.reader.CSVReader;
import com.walterjwhite.csv.api.service.reader.CSVReaderProducer;
import java.io.Reader;

public class UnivocityCSVReaderProducer implements CSVReaderProducer {

  @Override
  public CSVReader get(Reader reader, char delimeter, String... columns) {
    final CsvParserSettings csvParserSettings = new CsvParserSettings();
    csvParserSettings.getFormat().setLineSeparator("\n");
    csvParserSettings.setHeaderExtractionEnabled(true);
    csvParserSettings.getFormat().setDelimiter(delimeter);

    final CsvParser csvParser = new CsvParser(csvParserSettings);
    csvParser.beginParsing(reader);
    return new UnivocityCSVReader(csvParser, columns);
  }
}
