package com.walterjwhite.email.javamail.authentication;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordAuthenticator extends Authenticator {
  protected final PasswordAuthentication passwordAuthentication;

  protected PasswordAuthentication getPasswordAuthentication() {
    return passwordAuthentication;
  }
}
