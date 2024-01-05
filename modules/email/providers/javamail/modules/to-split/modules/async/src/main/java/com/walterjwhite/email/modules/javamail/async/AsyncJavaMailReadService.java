// package com.walterjwhite.email.modules.javamail.async;
// import com.walterjwhite.email.api.model.PrivateEmailAccount;
// import com.walterjwhite.email.api.service.EmailReadService;
// import com.walterjwhite.email.impl.property.EmailFolder;
// import com.walterjwhite.infrastructure.inject.core.service.ShutdownAware;
// import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
// import com.walterjwhite.property.api.annotation.Property;
// import jakarta.inject.Inject;
// import javax.mail.event.MessageCountAdapter;
/// ** Monitors an inbox for new messages. */
// public class AsyncJavaMailReadService implements /*EmailReadService,*/ StartupAware,
// ShutdownAware {
//  protected final transient Thread imapIdleThread;
//  @Inject
//  public AsyncJavaMailReadService(
//      MessageCountAdapter messageCountAdapter,
//      PrivateEmailAccount privateEmailAccount,
//      @Property(EmailFolder.class) String folderName) {
//    imapIdleThread = new Thread(new ImapIdleThread(messageCountAdapter, privateEmailAccount,
// folderName));
//  }
////  @Override
////  public void read(PrivateEmailAccount privateEmailAccount) {
////    throw new UnsupportedOperationException();
////  }
//  @Override
//  public void onStartup() {
//    imapIdleThread.start();
//  }
//  @Override
//  public void onShutdown() {
//    imapIdleThread.interrupt();
//  }
// }
