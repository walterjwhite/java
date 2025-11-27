package com.walterjwhite.encryption.model;

import com.walterjwhite.encryption.enumeration.EncryptionDataType;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class EncryptionConfiguration {

  protected EncryptionDataType encryptionDataType;
}
