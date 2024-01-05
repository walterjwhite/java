package com.walterjwhite.authentication.api.model.command;

import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class DeleteUserCommand extends AbstractAccountCommand {}
