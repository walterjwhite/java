TODO: commented @ 2021/09/08 - not used for a very long time, changed browser API
package com.walterjwhite.remote.impl.plugins.browser;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.event.annotation.SubscribeTo;
@SubscribeTo(eventClass = BrowserCallableMessage.class)
public class BrowserRunnableMessageHandlerService extends AbstractRunnable {
 @Override
 protected void doCall() throws Exception {

 }
}
