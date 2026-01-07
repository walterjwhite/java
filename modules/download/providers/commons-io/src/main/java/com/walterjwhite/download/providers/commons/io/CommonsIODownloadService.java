package com.walterjwhite.download.providers.commons.io;

import com.walterjwhite.download.impl.DownloadConfiguration;
import com.walterjwhite.download.impl.service.AbstractDownloadService;
import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import jakarta.inject.Inject;
import java.io.File;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class CommonsIODownloadService extends AbstractDownloadService {

  @Inject
  public CommonsIODownloadService(
      DigestAlgorithm digestAlgorithm, /*@Property(DownloadPath.class) String downloadPath*/
      DownloadConfiguration downloadConfiguration) {
    super(digestAlgorithm, downloadConfiguration);
  }


  protected void doDownload(final String uri, final File downloadFile) throws Exception {
    FileUtils.copyURLToFile(new URL(uri), downloadFile);
  }
}
