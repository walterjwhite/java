package com.walterjwhite.exchange;

import com.walterjwhite.file.api.service.FileEntityOutputStream;
import com.walterjwhite.file.api.service.FileStorageService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.AttachmentCollection;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import org.apache.commons.io.IOUtils;

public class AbstractExchangeService {
  protected final ExchangeService exchangeService;
  protected final FileStorageService fileStorageService;

  public AbstractExchangeService(
      ExchangeService exchangeService, FileStorageService fileStorageService) {
    this.exchangeService = exchangeService;
    this.fileStorageService = fileStorageService;
  }

  protected void getAttachments(Item item) throws Exception {

    /*
    item.getDateTimeSent()
            created
                    updated
    */
    if (hasAttachments(item)) {
      // item.getAllowedResponseActions();

      final AttachmentCollection attachments = item.getAttachments();
      // LOGGER.info("attachments:" + attachments.getCount());

      for (Attachment attachment : attachments.getItems()) {
        com.walterjwhite.file.api.model.File file = getAttachment(attachment);
      }
    }
  }

  protected boolean hasAttachments(Item item) throws ServiceLocalException {
    return item.getHasAttachments();
  }

  protected com.walterjwhite.file.api.model.File getAttachment(Attachment attachment)
      throws Exception {
    if (attachment instanceof FileAttachment) {
      attachment.load();

      FileAttachment fileAttachment = (FileAttachment) attachment;
      if (hasContent(fileAttachment)) {
        try (final InputStream is = new ByteArrayInputStream(fileAttachment.getContent())) {
          try (final FileEntityOutputStream feos = new FileEntityOutputStream(fileStorageService)) {
            IOUtils.copy(is, feos);
            feos.flush();

            com.walterjwhite.file.api.model.File file = feos.getFile();
            file.withFilename(fileAttachment.getName());
            // file.withExtension();
            //        final int index = fileAttachment.getName().lastIndexOf(".");
            //        final String extension;
            //        if (index >= 0) {
            //          extension = fileAttachment.getName().substring(index + 1);
            //        } else {
            //          extension = "";
            //        }

            return file;
          }
        }
      }
    }

    return null;
  }

  protected boolean hasContent(FileAttachment fileAttachment) throws ServiceVersionException {
    return (fileAttachment.getSize() > 65);
  }
}
