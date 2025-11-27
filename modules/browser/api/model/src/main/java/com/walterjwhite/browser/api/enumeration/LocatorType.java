package com.walterjwhite.browser.api.enumeration;


public enum LocatorType {
  XPath /*{
          public By get(final String argument) {
            return By.xpath(argument);
          }
        }*/,
  Id /*{
       public By get(final String argument) {
         return By.id(argument);
       }
     },*/;

}
