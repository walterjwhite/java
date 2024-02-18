package com.walterjwhite.calendar.modules.exchange;

import com.walterjwhite.calendar.api.model.CalendarAttachment;
import com.walterjwhite.calendar.api.model.CalendarEvent;

import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import com.walterjwhite.exchange.AbstractExchangeService;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.person.api.model.Person;
import jakarta.inject.Inject;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.service.DeleteMode;
import microsoft.exchange.webservices.data.core.enumeration.service.SendCancellationsMode;
import microsoft.exchange.webservices.data.core.enumeration.service.SendInvitationsMode;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.CalendarView;
import microsoft.exchange.webservices.data.search.FindItemsResults;

public class DefaultExchangeCalendarService
    extends AbstractExchangeService  {

  protected final ExchangeService exchangeService;

  protected final FileStorageService fileStorageService;

  protected final DigestAlgorithm digestAlgorithm;




  @Inject
  public DefaultExchangeCalendarService(
      ExchangeService exchangeService,
      FileStorageService fileStorageService,
      DigestAlgorithm digestAlgorithm) {
    super(exchangeService, fileStorageService);
    this.exchangeService = exchangeService;
    this.fileStorageService = fileStorageService;
    this.digestAlgorithm = digestAlgorithm;
  }


  public void save(CalendarEvent calendarEvent) throws Exception {
    Appointment appointment = new Appointment(exchangeService);
    appointment.setSubject(calendarEvent.getSubject());
    appointment.setLocation(calendarEvent.getLocation());
    appointment.setBody(MessageBody.getMessageBodyFromText(calendarEvent.getDescription()));

    for (Person person : calendarEvent.getToRecipients()) {

    }

    for (Person person : calendarEvent.getCcRecipients()) {

    }






    if (!calendarEvent.getFiles().isEmpty()) {
      for (CalendarAttachment calendarAttachment : calendarEvent.getFiles()) {




      }
    }

    appointment.save(SendInvitationsMode.SendToAllAndSaveCopy);
    calendarEvent.setServerId(UUID.randomUUID().toString());

  }


  public void delete(CalendarEvent calendarEvent) throws Exception {
    Appointment appointment =
        Appointment.bind(exchangeService, ItemId.getItemIdFromString(calendarEvent.getServerId()));
    appointment.delete(DeleteMode.MoveToDeletedItems, SendCancellationsMode.SendOnlyToAll);

  }

  

  public void read() throws Exception {
    final CalendarView view = new CalendarView(Date.from(Instant.now()), Date.from(Instant.now()));
    readItemsInFolder(new FolderId(WellKnownFolderName.Calendar), view);
  }

  protected void readItemsInFolder(FolderId folderId, CalendarView view)
      throws Exception {
    FindItemsResults<Appointment> findResults;
    do {
      findResults = exchangeService.findAppointments(folderId, view);
      for (Appointment appointment : findResults.getItems()) {
        readItem(appointment);
      }


    } while (findResults.isMoreAvailable());
  }

  protected void readItem(Appointment appointment) throws Exception {







    getAttachments(appointment);


  }
}
