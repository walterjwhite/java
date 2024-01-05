package com.walterjwhite.encryption.enumeration;

/*

AES/CBC/NoPadding (128)
AES/CBC/PKCS5Padding (128)
AES/ECB/NoPadding (128)
AES/ECB/PKCS5Padding (128)
DES/CBC/NoPadding (56)
DES/CBC/PKCS5Padding (56)
DES/ECB/NoPadding (56)
DES/ECB/PKCS5Padding (56)
DESede/CBC/NoPadding (168)
DESede/CBC/PKCS5Padding (168)
DESede/ECB/NoPadding (168)
DESede/ECB/PKCS5Padding (168)
RSA/ECB/PKCS1Padding (1024, 2048)
RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)

*/

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
  // PBE ALGORITHMS:      [PBEWITHMD5ANDDES, PBEWITHMD5ANDTRIPLEDES, PBEWITHSHA1ANDDESEDE,
  // PBEWITHSHA1ANDRC2_40]

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
