package com.walterjwhite.authentication.api.model.command;

import com.walterjwhite.identity.api.model.account.ClientAccount;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class AbstractAccountCommand {

  protected ClientAccount account;

  protected LocalDateTime requestDateTime;
}
