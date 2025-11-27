package com.walterjwhite.download.impl.service;

import com.walterjwhite.download.api.model.Download;
import com.walterjwhite.download.api.service.DownloadService;
import com.walterjwhite.download.impl.DownloadConfiguration;
import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public abstract class AbstractDownloadService implements DownloadService {
  protected final DigestAlgorithm digestAlgorithm;
  protected final DownloadConfiguration downloadConfiguration;

  public AbstractDownloadService(
      DigestAlgorithm digestAlgorithm, /*@Property(DownloadPath.class) String downloadPath*/
      DownloadConfiguration downloadConfiguration) {
    this.digestAlgorithm = digestAlgorithm;
    this.downloadConfiguration = downloadConfiguration;
  }

  protected boolean isValid(final File file, final String signature)
      throws IOException, NoSuchAlgorithmException {
    return (file.exists() && digestAlgorithm.matches(file, signature));
  }

  protected boolean isAlreadyDownloaded(final String signature, final File downloadFile)
      throws IOException, NoSuchAlgorithmException {
    if (downloadFile.exists()) {
      if (signature != null) {
        if (!isValid(downloadFile, signature)) {
          downloadFile.delete();
          return false;
        }

        return true;
      }

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

    if (download.getSignature() != null && !isValid(downloadFile, download.getSignature())) {
      throw new IllegalStateException(
          String.format(
              "Signature does not match:%s %s",
              download.getSignature(), downloadFile.getAbsolutePath()));
    }

    return downloadFile;
  }

  public File download(Download download, final File checksumFile) throws Exception {
    if (download.getSignature() == null) {
      download.setSignature(digestAlgorithm.getSignatureFromFile(checksumFile));
    }

    return download(download);
  }

  protected abstract void doDownload(final String uri, final File downloadFile) throws Exception;
}
