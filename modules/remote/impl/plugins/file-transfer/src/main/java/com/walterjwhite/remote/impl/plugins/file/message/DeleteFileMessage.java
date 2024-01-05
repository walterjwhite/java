package com.walterjwhite.remote.impl.plugins.file.message;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.remote.api.model.message.Message;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Used to delete a specific file from the machine. */
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class DeleteFileMessage extends Message {

  protected File file;

  /** Absolute path where this file should be moved * */
  protected String target;
}
