package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.print.enumeration.PrinterType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@PersistenceCapable
@NoArgsConstructor
public class Printer extends AbstractNamedEntity {

  protected Location location;

  protected String uri;

  protected PrinterType printerType;

  public Printer(
      String name, String description, Location location, String uri, PrinterType printerType) {
    super(name, description);

    this.location = location;
    this.uri = uri;
    this.printerType = printerType;
  }
}
