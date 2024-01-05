package com.walterjwhite.file.providers.local.service;

import com.walterjwhite.file.api.model.File;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
public class FileDBEntry extends File {
  protected byte[] data;

  public FileDBEntry(String checksum, byte[] data) {
    super(checksum);
    this.data = data;
  }
}
