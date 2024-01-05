package com.walterjwhite.encryption.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import com.walterjwhite.encryption.enumeration.*;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class EncryptionPolicy extends AbstractNamedEntity {
  @EqualsAndHashCode.Exclude protected EncryptionPolicy parentPolicy;

  @EqualsAndHashCode.Exclude protected Set<EncryptionPolicy> childrenPolicies;

  protected CipherMode cipherMode;

  protected DigestAlgorithm digestAlgorithm;

  protected EncryptionAlgorithm encryptionAlgorithm;

  protected PaddingType paddingType;

  protected TransformationAlgorithm transformationAlgorithm;
}
