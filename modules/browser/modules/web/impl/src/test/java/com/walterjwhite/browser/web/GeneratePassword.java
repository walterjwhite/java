package com.walterjwhite.browser.web;

import com.walterjwhite.identity.api.service.PasswordGenerator;

public class GeneratePassword {
  public static void main(final String[] args) {
    System.out.println(PasswordGenerator.generate(null));
  }
}
