package com.walterjwhite.authentication.api.model.command;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class AbstractAccountCommand extends AbstractEntity {

  protected ClientAccount account;

  protected LocalDateTime requestDateTime;
}
