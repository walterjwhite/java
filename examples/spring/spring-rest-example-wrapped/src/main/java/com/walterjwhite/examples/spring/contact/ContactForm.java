package com.walterjwhite.examples.spring.contact;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactForm {
  @NotBlank
  @Size(max = 100)
  private String name;

  @NotBlank
  @Email
  @Size(max = 254)
  private String email;

  @NotBlank
  @Size(max = 200)
  private String subject;

  @NotBlank
  @Size(max = 2000)
  private String message;

  public void validate() {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name is required");
    }
    if (name.length() > 100) {
      throw new IllegalArgumentException("Name is too long (max 100 characters)");
    }

    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email is required");
    }
    if (email.length() > 254) {
      throw new IllegalArgumentException("Email is too long (max 254 characters)");
    }
    try {
      InternetAddress addr = new InternetAddress(email);
      addr.validate();
    } catch (AddressException ex) {
      throw new IllegalArgumentException("Email address is invalid");
    }

    if (subject == null || subject.trim().isEmpty()) {
      throw new IllegalArgumentException("Subject is required");
    }
    if (subject.length() > 200) {
      throw new IllegalArgumentException("Subject is too long (max 200 characters)");
    }

    if (message == null || message.trim().isEmpty()) {
      throw new IllegalArgumentException("Message is required");
    }
    if (message.length() > 2000) {
      throw new IllegalArgumentException("Message is too long (max 2000 characters)");
    }
  }
}
