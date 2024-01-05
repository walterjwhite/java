package com.walterjwhite.data.pipe.modules.email;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class EmailSourceConfiguration extends AbstractSourceConfiguration {
  protected String folderName;

  protected PrivateEmailAccount emailAccount;

  public EmailSourceConfiguration(final String folderName, final PrivateEmailAccount emailAccount) {
    super(EmailSource.class);

    this.folderName = folderName;
    this.emailAccount = emailAccount;
  }
}
