package com.walterjwhite.email.modules.exchange;


import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import com.walterjwhite.exchange.AbstractExchangeService;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.api.service.FileStorageService;
import jakarta.inject.Inject;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindFoldersResults;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.FolderView;
import microsoft.exchange.webservices.data.search.ItemView;

public class DefaultExchangeEmailService
    extends AbstractExchangeService  {
  protected final DigestAlgorithm digestAlgorithm;

  @Inject
  public DefaultExchangeEmailService(
      ExchangeService exchangeService,
      FileStorageService fileStorageService,
      DigestAlgorithm digestAlgorithm) {
    super(exchangeService, fileStorageService);
    this.digestAlgorithm = digestAlgorithm;
  }


  public void send(Email email) throws Exception {
    EmailMessage msg = new EmailMessage(exchangeService);
    msg.setSubject(email.getSubject());
    msg.setBody(MessageBody.getMessageBodyFromText(email.getBody()));

    for (File file : email.getFiles()) {



    }













    msg.send();
  }

  

  public void read() throws Exception {
    final ItemView view = new ItemView(50);
    read(new FolderId(WellKnownFolderName.Inbox), view);
  }

  protected void readItemsInFolder(FolderId folderId, ItemView view)
      throws Exception {
    FindItemsResults<Item> findResults;
    int i = 0;
    do {
      findResults = exchangeService.findItems(folderId, view);




      if (findResults != null && findResults.getItems().size() > 0) {
        exchangeService.loadPropertiesForItems(
            findResults,
            new PropertySet(BasePropertySet.FirstClassProperties, EmailMessageSchema.Attachments));
      }



      for (Item item : findResults.getItems()) {
        processItem(item);
      }

      view.setOffset(view.getOffset() + 50);
    } while (findResults.isMoreAvailable());
  }

  protected void processItem(Item item) throws Exception {
    getAttachments(item);


  }

  protected void iterateThroughFolders(FolderId folderId, ItemView view)
      throws Exception {
    FindFoldersResults findFoldersResults =
        exchangeService.findFolders(folderId, new FolderView(Integer.MAX_VALUE));

    for (Folder folder : findFoldersResults.getFolders()) {
      view.setOffset(0);
      read(folder.getId(), view);
    }
  }

  protected void read(FolderId folderId, ItemView view) throws Exception {

    readItemsInFolder(folderId, view);
    iterateThroughFolders(folderId, view);
  }
}
