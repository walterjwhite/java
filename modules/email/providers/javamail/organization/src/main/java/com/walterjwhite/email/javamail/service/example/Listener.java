package com.walterjwhite.email.javamail.service.example;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.angus.mail.imap.IMAPFolder;

public class Listener {

  protected final Map<IMAPFolder, Thread> folderThreadMap = new HashMap<>();








  public void start(IMAPFolder imapFolder) {
    if (folderThreadMap.containsKey(imapFolder)) return;

    
    final Thread thread =
        null; 
    thread.start();
    folderThreadMap.put(imapFolder, thread);
  }

  public void stop(IMAPFolder imapFolder) {
    final Thread thread = folderThreadMap.get(imapFolder);
    if (thread != null) {
      if (thread.isAlive()) {
        thread.interrupt();
      }

      folderThreadMap.remove(imapFolder);
    }
  }
}
