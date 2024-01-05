TODO: commented @ 2021/09/08 - not used for a very long time, changed browser API
package com.walterjwhite.remote.impl.plugins.browser;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.event.annotation.SubscribeTo;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
@SubscribeTo(eventClass = BrowserScriptMessage.class)
@RequiredArgsConstructor
public class BrowserScriptMessageHandlerService extends AbstractRunnable {
 protected final WebDriver webDriver;
 @Override
 protected void doCall() throws Exception {
   //    //browserService.get();
 }
}
