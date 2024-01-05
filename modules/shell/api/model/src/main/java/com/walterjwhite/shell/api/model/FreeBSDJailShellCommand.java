package com.walterjwhite.shell.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@ToString(doNotUseGetters = true, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class FreeBSDJailShellCommand extends ChrootShellCommand implements Chrootable {
  protected String jailName;
}
