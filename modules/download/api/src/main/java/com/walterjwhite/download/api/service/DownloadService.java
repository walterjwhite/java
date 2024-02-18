package com.walterjwhite.download.api.service;

import com.walterjwhite.download.api.model.Download;
import java.io.File;

public interface DownloadService {
  File download(Download download) throws Exception;

  File download(Download download, File checksumFile) throws Exception;

  static String getFilename(final String uri) {
    final int index = uri.lastIndexOf("/");
    return uri.substring(index + 1);
  }
}
