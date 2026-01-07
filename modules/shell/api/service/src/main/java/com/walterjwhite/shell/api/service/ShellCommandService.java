package com.walterjwhite.shell.api.service;

public interface ShellCommandService<EntityType /*extends Serializable*/> {
  EntityType execute(EntityType entityType) throws Exception;
}
