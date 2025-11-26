package com.walterjwhite.print.model;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.print.enumeration.PrinterType;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrintRequest {

  protected Location location;

  protected PrinterType printerType;

  protected File file;

  protected LocalDateTime requestDateTime;

  @EqualsAndHashCode.Exclude protected transient String password;

  @EqualsAndHashCode.Exclude protected String passwordEncrypted;

  @EqualsAndHashCode.Exclude protected String passwordSalt;

  @EqualsAndHashCode.Exclude protected Set<PrintJob> printJobs = new HashSet<>();
}
