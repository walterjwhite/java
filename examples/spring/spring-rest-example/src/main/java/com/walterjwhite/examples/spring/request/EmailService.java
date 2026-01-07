package com.walterjwhite.examples.spring.request;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender emailSender;

  @Value("${spring.mail.username}")
  private String from;

  @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000))
  public void send(final String to, final String subject, final String text) {
    final SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    message.setFrom(from);

    emailSender.send(message);
  }
}
