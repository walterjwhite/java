package com.walterjwhite.datastore.api.model.entity;

/**
 * TODO: investigate migrating this back to a table so that this can be dynamic, CSV, Database, etc.
 */
@Deprecated
public enum EntityContainerType {
  File,
  AWS_S3,
  Google_Cloud,
  Database
}
