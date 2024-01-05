package com.walterjwhite.browser.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
// import com.walterjwhite.browser.api.enumeration.LocatorType;
// import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
// import com.walterjwhite.browser.api.util.WebDriverUtil;
// import org.openqa.selenium.WebDriver;

@Getter
@RequiredArgsConstructor
public enum WebDriverAction {
//    Locate{
//            public void execute(
//                    final WebDriver webDriver,
//                    final BrowserUserConfiguration browserUserConfiguration,
//                    final String... arguments)
//                    throws InterruptedException {
//              final LocatorType locatorType = LocatorType.valueOf(arguments[0]);
//              WebDriverUtil.click(
//                      browserUserConfiguration.getBrowserActionDelayableMap().get(this),
//                      webDriver.findElement(locatorType.get(arguments[1])));
//            }
//          },
//    Click{
//        public void execute(
//                final WebDriver webDriver,
//                final BrowserUserConfiguration browserUserConfiguration,
//                final String... arguments)
//                throws InterruptedException {
//            final LocatorType locatorType = LocatorType.valueOf(arguments[0]);
//            WebDriverUtil.click(
//                    browserUserConfiguration.getBrowserActionDelayableMap().get(this),
//                    webDriver.findElement(locatorType.get(arguments[1])));
//        }
//    },
//    Sendkeys{
//        public void execute(
//                final WebDriver webDriver,
//                final BrowserUserConfiguration browserUserConfiguration,
//                final String... arguments)
//                throws InterruptedException {
//            final LocatorType locatorType = LocatorType.valueOf(arguments[0]);
//            final boolean clear;
//            if (arguments.length == 3) {
//                clear = false;
//            } else if (arguments.length == 4) {
//                clear = Boolean.valueOf(arguments[2]);
//            } else {
//                throw new IllegalStateException("Expected either 3 or 4 arguments:" +
// arguments.length);
//            }
//            WebDriverUtil.sendKeys(
//                    browserUserConfiguration.getBrowserActionDelayableMap().get(this),
//                    webDriver.findElement(locatorType.get(arguments[1])),
//                    arguments[2],
//                    clear);
//        }
//    },
//    Type{
//        public void execute(
//                final WebDriver webDriver,
//                final BrowserUserConfiguration browserUserConfiguration,
//                final String... arguments)
//                throws InterruptedException {
//            final LocatorType locatorType = LocatorType.valueOf(arguments[0]);
//            final boolean clear;
//            if (arguments.length == 3) {
//                clear = false;
//            } else if (arguments.length == 4) {
//                clear = Boolean.valueOf(arguments[2]);
//            } else {
//                throw new IllegalStateException("Expected either 3 or 4 arguments:" +
// arguments.length);
//            }
//            WebDriverUtil.type(
//                    browserUserConfiguration.getBrowserActionDelayableMap().get(this),
//                    webDriver.findElement(locatorType.get(arguments[1])),
//                    arguments[2],
//                    clear);
//        }
//    },
//    Execute;
//    private final BrowserAction browserAction;
//    public abstract void execute(
//            final WebDriver webDriver,
//            final BrowserUserConfiguration browserUserConfiguration,
//            final String... arguments)
//            throws InterruptedException;
}
