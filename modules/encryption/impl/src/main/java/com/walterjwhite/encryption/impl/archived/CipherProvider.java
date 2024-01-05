// package com.walterjwhite.encryption.impl;
// import java.security.NoSuchAlgorithmException;
// import javax.crypto.Cipher;
// import javax.crypto.NoSuchPaddingException;
// import jakarta.inject.Inject;
// import jakarta.inject.Provider;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// public class CipherProvider implements Provider<Cipher> {

//  protected final EncryptionConfiguration encryptionConfiguration;
//  @Inject
//  public CipherProvider(EncryptionConfiguration encryptionConfiguration) {
//    this.encryptionConfiguration = encryptionConfiguration;
//  }
//  @Override
//  public Cipher get() {
//    try {
//      return Cipher.getInstance(
//          encryptionConfiguration.getEncryptionAlgorithm().name()
//              + "/"
//              + encryptionConfiguration.transformationAlgorithm.name()
//              + "/"
//              + encryptionConfiguration.getPaddingType().name());
//    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
//      LOGGER.error("Error initializing cipher", e);
//      throw new RuntimeException(e));
//    }
//  }
//  //  cipher.init(
//  //                cipherMode,
//  //                secretKey,
//  //                new IvParameterSpec(
//  //                        new
// BASE64Decoder().decodeBuffer(encryptionConfiguration.getInitializationVector())));
// }
