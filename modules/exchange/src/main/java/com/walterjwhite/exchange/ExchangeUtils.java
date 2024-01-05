package com.walterjwhite.exchange;

import com.walterjwhite.datastore.api.model.entity.Tag;
import microsoft.exchange.webservices.data.property.complex.FolderId;

public class ExchangeUtils {
  private ExchangeUtils() {}

  public static Tag getLabel(FolderId folderId, Tag parent) {
    if (folderId != null && folderId.getFolderName() != null) {
      return new Tag(folderId.getFolderName().toString(), parent);
    }

    return null;
  }
}
