package com.walterjwhite.shell.api.service;

import java.io.Serializable;

public interface ShellCommandService<EntityType > {
  EntityType execute(EntityType entityType) throws Exception;
}
