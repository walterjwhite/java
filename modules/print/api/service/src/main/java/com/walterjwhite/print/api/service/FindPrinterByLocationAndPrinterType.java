package com.walterjwhite.print.api.service;

import com.walterjwhite.datastore.query.AbstractQuery;
import com.walterjwhite.print.enumeration.PrinterType;
import com.walterjwhite.print.model.Location;
import com.walterjwhite.print.model.Printer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class FindPrinterByLocationAndPrinterType extends AbstractQuery<Printer, Printer> {
  protected final Location location;
  protected final PrinterType printerType;

  public FindPrinterByLocationAndPrinterType(
      int offset, int recordCount, Location location, PrinterType printerType) {
    super(offset, recordCount, Printer.class, Printer.class, false);
    this.location = location;
    this.printerType = printerType;
  }

  //    Predicate locationPredicate =
  //            criteriaBuilder.equal(
  //                    printerCriteriaQueryConfiguration.getRoot().get(Printer_.location),
  // location);
  //    Predicate printerTypePredicate =
  //            criteriaBuilder.equal(
  //                    printerCriteriaQueryConfiguration.getRoot().get(Printer_.printerType),
  // printerType);
}
