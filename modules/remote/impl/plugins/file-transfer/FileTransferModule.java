package com.walterjwhite.remote.impl.plugins.file;

import com.google.inject.AbstractModule;
import com.walterjwhite.remote.impl.plugins.file.presend.DeleteFilePreSendMessageListener;
import com.walterjwhite.remote.impl.plugins.file.presend.FileTransferPreSendMessageListener;

public class FileTransferModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(FileTransferPreSendMessageListener.class).asEagerSingleton();
    bind(DeleteFilePreSendMessageListener.class).asEagerSingleton();
  }
}
