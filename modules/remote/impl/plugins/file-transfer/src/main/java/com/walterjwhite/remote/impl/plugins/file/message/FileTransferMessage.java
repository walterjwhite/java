package com.walterjwhite.remote.impl.plugins.file.message;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.remote.api.model.message.Message;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class FileTransferMessage extends Message {
  protected File file;

  protected String target;
}
