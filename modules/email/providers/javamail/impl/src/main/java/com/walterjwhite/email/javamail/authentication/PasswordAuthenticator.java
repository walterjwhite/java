package com.walterjwhite.email.javamail.authentication;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class PasswordAuthenticator extends Authenticator {
  protected final PasswordAuthentication passwordAuthentication;

  public PasswordAuthenticator(PasswordAuthentication passwordAuthentication) {

    this.passwordAuthentication = passwordAuthentication;
  }

  protected PasswordAuthentication getPasswordAuthentication() {
    return passwordAuthentication;
  }
}
