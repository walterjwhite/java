package com.walterjwhite.identity.api.model.account;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.encryption.model.Encrypted;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class ChallengeResponse extends AbstractEntity {

  protected ClientAccount clientAccount;

  protected Challenge challenge;

  protected Encrypted response;
}
