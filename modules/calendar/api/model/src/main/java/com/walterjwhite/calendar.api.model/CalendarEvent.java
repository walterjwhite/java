package com.walterjwhite.calendar.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.person.api.model.Person;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class CalendarEvent extends AbstractEntity {
  //  @Id
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE)

  protected String serverId;

  @EqualsAndHashCode.Exclude protected Calendar calendar;

  @EqualsAndHashCode.Exclude protected LocalDateTime start;
  @EqualsAndHashCode.Exclude protected LocalDateTime end;

  @EqualsAndHashCode.Exclude protected String subject;
  @EqualsAndHashCode.Exclude protected String description;
  @EqualsAndHashCode.Exclude protected String location;

  @EqualsAndHashCode.Exclude protected Set<Person> toRecipients;
  @EqualsAndHashCode.Exclude protected Set<Person> ccRecipients;
  @EqualsAndHashCode.Exclude protected Set<Person> bccRecipients;

  @EqualsAndHashCode.Exclude protected Set<CalendarAttachment> files;

  public CalendarEvent(
      Calendar calendar,
      LocalDateTime start,
      LocalDateTime end,
      String subject,
      String description,
      String location,
      Set<Person> toRecipients,
      Set<Person> ccRecipients,
      Set<Person> bccRecipients) {
    this();

    this.calendar = calendar;
    this.start = start;
    this.end = end;
    this.subject = subject;
    this.description = description;
    this.location = location;

    if (toRecipients != null && !toRecipients.isEmpty()) this.toRecipients.addAll(toRecipients);
    if (ccRecipients != null && !ccRecipients.isEmpty()) this.ccRecipients.addAll(ccRecipients);
    if (bccRecipients != null && !bccRecipients.isEmpty()) this.bccRecipients.addAll(bccRecipients);
    // if (files != null && !files.isEmpty()) this.files.addAll(files);
  }

  public CalendarEvent() {

    toRecipients = new HashSet<>();
    ccRecipients = new HashSet<>();
    bccRecipients = new HashSet<>();
    files = new HashSet<>();
  }
}
