package com.walterjwhite.identity.api.model.account;

import com.walterjwhite.encryption.model.Encrypted;
import com.walterjwhite.identity.api.model.Principal;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class ClientAccount implements Principal {
  protected Encrypted clientId;

  protected List<ChallengeResponse> challengeResponses;

  public String getPrincipalId() {
    return clientId.getPlainText();
  }
}
