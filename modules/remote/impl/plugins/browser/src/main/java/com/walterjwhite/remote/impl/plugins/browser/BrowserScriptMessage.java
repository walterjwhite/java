TODO: commented @ 2021/09/08 - not used for a very long time, changed browser API
package com.walterjwhite.remote.impl.plugins.browser;
import com.walterjwhite.browser.api.model.BrowserScript;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString(doNotUseGetters = true)
@PersistenceCapable

public class BrowserScriptMessage extends Message {
 // browser manager class that remote controls the browser
 // or browser script, get, type, ...
 protected BrowserScript browserScript;
 public BrowserScriptMessage(Set<Client> recipients, int timeToLive, BrowserScript browserScript)
{
   super(recipients, timeToLive);
   this.browserScript = browserScript;
 }
 public BrowserScriptMessage() {
 }
}
