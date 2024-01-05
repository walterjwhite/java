package com.walterjwhite.download.providers.commons.io;

import com.walterjwhite.download.impl.DownloadConfiguration;
import com.walterjwhite.download.impl.service.AbstractDownloadService;
import com.walterjwhite.encryption.service.DigestService;
import java.io.File;
import java.net.URL;
import jakarta.inject.Inject;
import org.apache.commons.io.FileUtils;

public class CommonsIODownloadService extends AbstractDownloadService {

  @Inject
  public CommonsIODownloadService(
      DigestService digestService, /*@Property(DownloadPath.class) String downloadPath*/
      DownloadConfiguration downloadConfiguration) {
    super(digestService, downloadConfiguration);
  }

  //
  //    @Inject
  //    public CommonsIODownloadService(DigestService digestService, BuildConfiguration
  // buildConfiguration) {
  //        this.digestService = digestService;
  //        this.downloadPath = buildConfiguration.getRootDirectory()
  //                + File.separator
  //                + "tmp/downloads";
  //    }

  protected void doDownload(final String uri, final File downloadFile) throws Exception {
    FileUtils.copyURLToFile(new URL(uri), downloadFile);
  }
}
