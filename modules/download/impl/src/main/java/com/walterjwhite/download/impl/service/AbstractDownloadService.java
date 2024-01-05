package com.walterjwhite.download.impl.service;

import com.walterjwhite.download.api.model.Download;
import com.walterjwhite.download.api.service.DownloadService;
import com.walterjwhite.download.impl.DownloadConfiguration;
import com.walterjwhite.encryption.service.DigestService;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public abstract class AbstractDownloadService implements DownloadService {
  protected final DigestService digestService;
  //  protected final String downloadPath;
  protected final DownloadConfiguration downloadConfiguration;

  public AbstractDownloadService(
      DigestService digestService, /*@Property(DownloadPath.class) String downloadPath*/
      DownloadConfiguration downloadConfiguration) {
    this.digestService = digestService;
    //    this.downloadPath = downloadPath;
    this.downloadConfiguration = downloadConfiguration;
  }

  protected boolean isValid(final File file, final String signature)
      throws IOException, NoSuchAlgorithmException {
    return (file.exists() && digestService.matches(file, signature));
  }

  protected boolean isAlreadyDownloaded(final String signature, final File downloadFile)
      throws IOException, NoSuchAlgorithmException {
    if (downloadFile.exists()) {
      if (signature != null) {
        if (!isValid(downloadFile, signature)) {
          // remove the file and re-download
          downloadFile.delete();
          return false;
        }

        return true;
      }

      // if the download signature is null, just assume the download is okay
      return true;
    }

    return false;
  }

  protected File getDownloadFile(final String filename) {
    return (new File(downloadConfiguration.getDownloadPath() + File.separator + filename));
  }

  public File download(Download download) throws Exception {
    final File downloadFile = getDownloadFile(download.getFilename());

    if (!isAlreadyDownloaded(download.getSignature(), downloadFile)) {
      doDownload(download.getUri(), downloadFile);
    }

    if (download.getSignature() != null && !isValid(downloadFile, download.getSignature()))
      throw new IllegalStateException(
          String.format(
              "Signature does not match:%s %s",
              download.getSignature(), downloadFile.getAbsolutePath()));

    return downloadFile;
  }

  public File download(Download download, final File checksumFile) throws Exception {
    if (download.getSignature() == null)
      download.setSignature(digestService.getSignatureFromFile(checksumFile));

    return download(download);
  }

  protected abstract void doDownload(final String uri, final File downloadFile) throws Exception;
}
