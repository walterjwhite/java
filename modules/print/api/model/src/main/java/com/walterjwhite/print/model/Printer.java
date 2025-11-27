package com.walterjwhite.print.model;

import com.walterjwhite.print.enumeration.PrinterType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@PersistenceCapable
@NoArgsConstructor
@AllArgsConstructor
public class Printer {
  protected String name;
  protected String description;

  protected Location location;

  protected String uri;

  protected PrinterType printerType;
}
