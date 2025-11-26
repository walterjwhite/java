package com.walterjwhite.file.api.model;

import java.time.LocalDateTime;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
@NoArgsConstructor
public class File {
  @EqualsAndHashCode.Exclude @NotPersistent protected transient String source;

  protected String checksum;

  @EqualsAndHashCode.Exclude protected LocalDateTime createdDateTime = LocalDateTime.now();

  @EqualsAndHashCode.Exclude protected String name;
  @EqualsAndHashCode.Exclude protected String extension;


  public File(String source, String checksum) {

    this.source = source;
    this.checksum = checksum;
  }

  public File(String source) {

    this.source = source;
  }

  public File withFilename(final String filename) {
    final int index = filename.lastIndexOf(".");
    if (index > 0) {
      name = filename.substring(0, index);
      extension = filename.substring(index + 1);
    }

    return this;
  }
}
