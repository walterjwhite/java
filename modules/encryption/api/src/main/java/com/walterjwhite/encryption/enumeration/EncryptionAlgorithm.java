package com.walterjwhite.encryption.enumeration;



import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import lombok.Getter;

@Getter
public enum EncryptionAlgorithm implements ConfigurableProperty {
  @DefaultValue
  AES(
      "AES",
      new CipherMode[] {CipherMode.CBC, CipherMode.ECB},
      new PaddingType[] {PaddingType.NoPadding, PaddingType.PKCS5Padding},
      128,
      256,
      512),
  DES(
      "DES",
      new CipherMode[] {CipherMode.CBC, CipherMode.ECB},
      new PaddingType[] {PaddingType.NoPadding, PaddingType.PKCS5Padding},
      56),
  DESede(
      "DESede",
      new CipherMode[] {CipherMode.CBC, CipherMode.ECB},
      new PaddingType[] {PaddingType.NoPadding, PaddingType.PKCS5Padding},
      168),
  MessageDigest(
      "MD",
      new CipherMode[] {CipherMode.CBC, CipherMode.ECB},
      new PaddingType[] {PaddingType.NoPadding, PaddingType.PKCS5Padding},
      2,
      5),
  RSA(
      "RSA",
      new CipherMode[] {CipherMode.ECB},
      new PaddingType[] {
        PaddingType.PKCS1Padding,
        PaddingType.OAEPWithSHA_1AndMGF1Padding,
        PaddingType.OAEPWithSHA_256AndMGF1Padding
      },
      1024,
      2048),
  SHA(
      "SHA",
      new CipherMode[] {CipherMode.CBC, CipherMode.ECB},
      new PaddingType[] {PaddingType.NoPadding, PaddingType.PKCS5Padding},
      0,
      1,
      256,
      384,
      512);



  private final String algorithmName;
  private final CipherMode[] cipherModes;
  private final PaddingType[] paddingTypes;
  private final int[] supportedSizes;

  EncryptionAlgorithm(
      final String algorithmName,
      final CipherMode[] cipherModes,
      final PaddingType[] paddingTypes,
      final int... supportedSizes) {
    this.algorithmName = algorithmName;
    this.cipherModes = cipherModes;
    this.paddingTypes = paddingTypes;
    this.supportedSizes = supportedSizes;
  }
}
