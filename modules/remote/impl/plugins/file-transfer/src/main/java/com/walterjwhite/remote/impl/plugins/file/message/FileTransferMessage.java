package com.walterjwhite.remote.impl.plugins.file.message;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.remote.api.model.message.Message;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Used to send a file from machine A to machine B. machine A will upload the file to a 3rd party.
 * machine B will download the file from that 3rd party.
 */
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class FileTransferMessage extends Message {
  protected File file;

  /** Absolute path where this file should be moved * */
  protected String target;
  /** Absolute path where this file should be moved * */
}
