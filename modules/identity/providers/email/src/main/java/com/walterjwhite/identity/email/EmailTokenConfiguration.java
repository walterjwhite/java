package com.walterjwhite.identity.email;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import java.io.Serializable;
import java.time.Duration;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class EmailTokenConfiguration implements Serializable {
  protected PrivateEmailAccount emailAccount;
  protected String folderName;

  protected String subjectRegex;

  protected Duration tokenTimeout;
}
