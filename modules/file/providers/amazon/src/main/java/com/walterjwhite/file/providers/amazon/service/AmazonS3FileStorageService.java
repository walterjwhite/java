package com.walterjwhite.file.providers.amazon.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.walterjwhite.amazon.property.AmazonRegion;
import com.walterjwhite.encryption.api.service.CompressionService;
import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.impl.service.AbstractFileStorageService;
import com.walterjwhite.file.providers.amazon.property.AmazonS3Bucket;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.Debug;
import com.walterjwhite.property.api.enumeration.NoOperation;
import com.walterjwhite.property.api.enumeration.ProxyType;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;
import jakarta.inject.Inject;
import java.io.FileOutputStream;

public class AmazonS3FileStorageService extends AbstractFileStorageService {
  
  protected final String bucketName;

  protected final AmazonS3 s3client;

  @Inject
  public AmazonS3FileStorageService(
      CompressionService compressionService,
      EncryptionService encryptionService,
      DigestAlgorithm digestAlgorithm,
      @Property(NoOperation.class) boolean nop,
      @Property(Debug.class) boolean debug,
      @Property(ProxyType.class) ProxyType proxyType,
      @Property(ProxyHost.class) String proxyHost,
      @Property(ProxyPort.class) int proxyPort,
      @Property(AmazonS3Bucket.class) String bucketName,
      @Property(AmazonRegion.class) Regions region) {

    super(compressionService, encryptionService, digestAlgorithm, nop, debug);
    this.bucketName = bucketName;

    ClientConfiguration clientConfiguration = new ClientConfiguration();

    setupProxy(clientConfiguration, proxyType, proxyHost, proxyPort);

    s3client =
        AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withClientConfiguration(clientConfiguration)
            .build();
  }

  protected void setupProxy(
      ClientConfiguration clientConfiguration,
      com.walterjwhite.property.api.enumeration.ProxyType proxyType,
      String proxyHost,
      int proxyPort) {
    if (com.walterjwhite.property.api.enumeration.ProxyType.HTTP.equals(proxyType))
      clientConfiguration.setProtocol(Protocol.HTTP);

    clientConfiguration.setProxyHost(proxyHost);
    clientConfiguration.setProxyPort(proxyPort);
  }









  @Override
  protected void doPut(File file) {

    doAmazonPut(bucketName, file.getChecksum() , file.getSource());
  }

  protected PutObjectResult doAmazonPut(
      final String bucketName, final String fileChecksum, final String fileSource) {
    return s3client.putObject(bucketName, fileChecksum, new java.io.File(fileSource));






  }

  @Override
  public void doGet(File file) throws Exception {
    final S3Object s3Object = getS3Object(file);

    final java.io.File outputFile =
        java.io.File.createTempFile(file.getChecksum() , "s3");

    try (final FileOutputStream fos = new FileOutputStream(outputFile)) {
      int read;
      while ((read = s3Object.getObjectContent().read()) >= 0) {
        fos.write(read);
      }

      file.setSource(outputFile.getAbsolutePath());
    }
  }

  protected S3Object getS3Object(File file) {
    return s3client.getObject(bucketName, file.getChecksum() );
  }

  @Override
  public void delete(File file) {
    s3client.deleteObject(bucketName, file.getChecksum() );
  }
}
