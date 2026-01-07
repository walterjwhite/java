package com.walterjwhite.citrix;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import lombok.extern.slf4j.Slf4j;
import org.htmlunit.WebClient;
import org.htmlunit.html.*;

@Slf4j
public class CitrixServiceImpl implements CitrixService {
  @Override
  public void run(final CitrixSession citrixSession, final AbstractCitrixExtension citrixExtension)
      throws Exception {
    try (final WebClient webClient = new WebClient()) {
      citrixSession.setContextValue(WebClient.class, webClient);

      final ForkJoinPool forkJoinPool = new ForkJoinPool();

      if (!authenticate(webClient, citrixSession)) {
        throw new RuntimeException("Failed to authenticate");
      }

      useLightVersion(citrixSession);
      final HtmlPage childPage = launch(citrixSession, 1);
      citrixExtension.setData(childPage);
      final ForkJoinTask task = forkJoinPool.submit(citrixExtension);
      task.get();
    } finally {
      if (citrixSession.getContextValue(HtmlPage.class) != null) {
        LOGGER.warn(
            "current page source: \n{}", citrixSession.getContextValue(HtmlPage.class).asXml());
      }
    }
  }

  public boolean authenticate(final WebClient webClient, final CitrixSession citrixSession)
      throws IOException {
    webClient.getOptions().setJavaScriptEnabled(true);
    webClient.getOptions().setCssEnabled(false);

    webClient.waitForBackgroundJavaScript(citrixSession.getBackgroundJavascriptTimeout());

    final HtmlPage page =
        webClient.getPage(citrixSession.getUrl() + "/logon/LogonPoint/tmindex.html");
    citrixSession.setContextValue(HtmlPage.class, page);
    LOGGER.warn("HtmlUnit – Welcome to HtmlUnit | {}", page.getTitleText());
    LOGGER.warn("Page Source: {}", page.asXml());

    final HtmlForm form =
        Util.waitForValue(() -> page.getForms().getFirst(), citrixSession.getPageLoadTimeout());
    if (form == null) {
      throw new RuntimeException("Unable to fetch login page");
    }

    final HtmlTextInput usernameInput = form.getInputByName("login");
    usernameInput.type(citrixSession.getCitrixCredentials().getUsername());

    final HtmlPasswordInput passwordInput = form.getInputByName("passwd");
    passwordInput.type(citrixSession.getCitrixCredentials().getPassword());

    final HtmlPasswordInput tokenInput = form.getInputByName("passwd1");
    tokenInput.type(
        citrixSession.getCitrixCredentials().getToken()
            + citrixSession.getCitrixCredentials().getEphemeralToken());

    final DomElement loginButton = page.getElementById("loginBtn");

    final HtmlPage authenticatedPage = loginButton.click();
    LOGGER.warn("after submit: {}", authenticatedPage);

    citrixSession.setContextValue(HtmlPage.class, authenticatedPage);
    return isAuthenticated(citrixSession);
  }

  protected boolean isAuthenticated(final CitrixSession citrixSession) {
    final HtmlElement userMenuButton =
        (HtmlElement)
            Util.waitForValue(
                () -> citrixSession.getContextValue(HtmlPage.class).getElementById("userMenuBtn"),
                citrixSession.getLoginTimeout());
    return userMenuButton != null;
  }

  protected void useLightVersion(final CitrixSession citrixSession) throws IOException {
    final DomElement useLightVersion =
        Util.waitForValue(
            () ->
                citrixSession
                    .getContextValue(HtmlPage.class)
                    .getElementById("changeclient-use-light-version"),
            10_000);
    if (useLightVersion != null) {
      LOGGER.info("switching to light version, htmlunit can only do light");

      try {
        useLightVersion.click();
      } catch (Exception e) {
        LOGGER.warn("error while switching to light version", e);
      }

      return;
    }

    LOGGER.info("useLightVersion not detected");
  }

  protected void fetchWeb(final WebClient webClient, final CitrixSession citrixSession)
      throws IOException {
    final HtmlPage page = webClient.getPage(citrixSession.getUrl() + "/Citrix/VdsexternalWeb");
    citrixSession.setContextValue(HtmlPage.class, page);
  }

  @Override
  public HtmlPage launch(final CitrixSession citrixSession, final int index) throws IOException {
    final HtmlPage currentPage = citrixSession.getContextValue(HtmlPage.class);
    final List<Object> matchingElements =
        Util.waitForValue(
            () ->
                currentPage.getByXPath(
                    String.format(
                        "//*[@id=\"home-screen\"]/div[2]/section[5]/div[5]/div/ul/li[%d]/a[1]/img",
                        index)),
            15_000);
    if (matchingElements == null) {
      throw new IllegalStateException("no elements matched");
    }
    if (matchingElements.size() != 1) {
      throw new IllegalStateException(
          "matched an unexpected number of elements: " + matchingElements.size());
    }

    return ((HtmlElement) matchingElements.get(0)).click();
  }
}
