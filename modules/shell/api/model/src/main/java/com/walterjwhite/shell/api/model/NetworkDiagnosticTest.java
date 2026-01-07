package com.walterjwhite.shell.api.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Data
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@NoArgsConstructor
public class NetworkDiagnosticTest {

  protected String fqdn;

}
