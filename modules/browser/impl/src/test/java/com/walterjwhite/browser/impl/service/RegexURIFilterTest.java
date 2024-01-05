package com.walterjwhite.browser.impl.service;

import com.walterjwhite.browser.api.model.URIFilterConfiguration;
import com.walterjwhite.browser.impl.service.urifilter.RegexURIFilter;

public class RegexURIFilterTest {

  public static void main(final String[] arguments) {
    final URIFilterConfiguration twitterUriFilterConfiguration =
        new URIFilterConfiguration("^twitter.com.*");
    final URIFilterConfiguration javascriptUriFilterConfiguration =
        new URIFilterConfiguration("^javascript:.*");
    final URIFilterConfiguration mailtoUriFilterConfiguration =
        new URIFilterConfiguration("^mailto:.*");
    final URIFilterConfiguration hashtagUriFilterConfiguration =
        new URIFilterConfiguration(".*#.*");
    final URIFilterConfiguration plusGoogleUriFilterConfiguration =
        new URIFilterConfiguration("plus.google.com/.*");
    final URIFilterConfiguration googleAccountsUriFilterConfiguration =
        new URIFilterConfiguration("accounts.google.com/.*");
    final URIFilterConfiguration facebookAccountsUriFilterConfiguration =
        new URIFilterConfiguration("(www\\.)?facebook.com/sharer/sharer.php\\?u.*");
    // final URIFilterConfiguration facebookAccountsUriFilterConfiguration = new
    // URIFilterConfiguration(".*facebook.com/sharer/sharer.php\\?u.*");

    final RegexURIFilter regexURIFilter = new RegexURIFilter(javascriptUriFilterConfiguration);
    final RegexURIFilter mailtoRegexURIFilter = new RegexURIFilter(mailtoUriFilterConfiguration);
    final RegexURIFilter hashtagRegexURIFilter = new RegexURIFilter(hashtagUriFilterConfiguration);
    final RegexURIFilter plusGoogleRegexURIFilter =
        new RegexURIFilter(plusGoogleUriFilterConfiguration);
    final RegexURIFilter accountsGoogleRegexURIFilter =
        new RegexURIFilter(googleAccountsUriFilterConfiguration);
    final RegexURIFilter facebookRegexURIFilter =
        new RegexURIFilter(facebookAccountsUriFilterConfiguration);

    LOGGER.debug("matches:" + regexURIFilter.matches("javascript:open(test);"));
    LOGGER.debug("matches:" + hashtagRegexURIFilter.matches("google.com/news#current"));
    LOGGER.debug("matches:" + mailtoRegexURIFilter.matches("mailto:Fred@gmail.com"));

    LOGGER.debug("matches:" + plusGoogleRegexURIFilter.matches("plus.google.com/user/abcd"));
    LOGGER.debug("matches:" + accountsGoogleRegexURIFilter.matches("accounts.google.com/login"));
    LOGGER.debug(
        "matches:" + facebookRegexURIFilter.matches("facebook.com/sharer/sharer.php?u=abcd"));
    LOGGER.debug(
        "matches:" + facebookRegexURIFilter.matches("www.facebook.com/sharer/sharer.php?u=abcd"));
  }
}
