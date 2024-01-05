// package com.walterjwhite.encryption.enumeration;
// import java.security.NoSuchAlgorithmException;
// import java.security.SecureRandom;
// import java.util.prefs.BackingStoreException;
// import java.util.prefs.Preferences;
// import javax.crypto.KeyGenerator;
// import javax.crypto.SecretKey;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import sun.misc.BASE64Encoder;

// public enum EncryptionPreference {
//  Password {
//    @Override
//    protected byte[] getData(final EncryptionConfigurationDatastore encryptionConfiguration)
//        throws NoSuchAlgorithmException {
//      final KeyGenerator keyGenerator =
//          KeyGenerator.getInstance(
//              encryptionConfiguration.getEncryptionAlgorithm().getAlgorithmName());
//      keyGenerator.init(encryptionConfiguration.getKeyLength());
//      final SecretKey secretKey = keyGenerator.generateKey();
//      return (secretKey.getEncoded());
//    }
//  },
//  InitializationVector {
//    @Override
//    protected byte[] getData(final EncryptionConfigurationDatastore encryptionConfiguration)
//        throws NoSuchAlgorithmException {
//      final byte[] random = new byte[encryptionConfiguration.getSaltLength()];
//      final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//      secureRandom.nextBytes(random);
//      return (random);
//    }
//  },
//  ;

//  public String get(final EncryptionConfigurationDatastore encryptionConfiguration)
//      throws BackingStoreException, NoSuchAlgorithmException {
//    final Preferences preferences = Preferences.userNodeForPackage(EncryptionPreference.class);
//    try {
//      final String value = preferences.get(name(), null);
//      if (value == null) {
//        return (getAndSet(encryptionConfiguration, preferences));
//      }
//      return (value);
//    } catch (Exception e) {
//      LOGGER.info("exception occurred while getting value", e);
//      return (getAndSet(encryptionConfiguration, preferences));
//    }
//  }
//  protected String getAndSet(
//      final EncryptionConfigurationDatastore encryptionConfiguration, final Preferences
// preferences)
//      throws BackingStoreException, NoSuchAlgorithmException {
//    final byte[] data = getData(encryptionConfiguration);
//    final String value = new BASE64Encoder().encode(data);
//    preferences.put(name(), value);
//    return (value);
//  }
//  protected abstract byte[] getData(final EncryptionConfigurationDatastore
// encryptionConfiguration)
//      throws NoSuchAlgorithmException;
// }
