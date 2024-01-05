package com.walterjwhite.print.api.service;

import com.walterjwhite.print.enumeration.PrinterType;
import com.walterjwhite.print.model.Location;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class FindPrinterByNameLocationAndPrinterType extends FindPrinterByLocationAndPrinterType {
  protected final String name;

  public FindPrinterByNameLocationAndPrinterType(
      int offset, int recordCount, Location location, PrinterType printerType, String name) {
    super(offset, recordCount, location, printerType);
    this.name = name;
  }

  //    Predicate namePredicate =
  //
  // criteriaBuilder.equal(printerCriteriaQueryConfiguration.getRoot().get(Printer_.name), name);
}
