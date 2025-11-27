package com.walterjwhite.print.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
public class PrintJob {

  protected Printer printer;

  protected PrintRequest printRequest;
}
