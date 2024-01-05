package com.walterjwhite.identity.api.model.account;

import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class SystemClientAccount extends ClientAccount {}
