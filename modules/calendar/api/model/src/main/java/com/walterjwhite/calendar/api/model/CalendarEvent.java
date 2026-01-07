package com.walterjwhite.calendar.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import com.walterjwhite.person.api.model.Person;
import java.time.LocalDateTime;
import java.util.Set;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class CalendarEvent extends AbstractEntity {
  @Column protected String serverId;

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
}
