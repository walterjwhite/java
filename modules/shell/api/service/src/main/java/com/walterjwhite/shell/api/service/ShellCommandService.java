package com.walterjwhite.shell.api.service;

import java.io.Serializable;

public interface ShellCommandService<EntityType extends Serializable> {
  EntityType execute(EntityType entityType) throws Exception;
}
