package com.walterjwhite.email.modules.template;


import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class SampleEntity {
  protected String subject;
  protected String recipientName;
  protected String senderName;

  public SampleEntity(String subject, String recipientName, String senderName) {
    this.subject = subject;
    this.recipientName = recipientName;
    this.senderName = senderName;
  }
}
