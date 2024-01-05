package com.walterjwhite.data.pipe.modules.email;

import com.walterjwhite.data.pipe.impl.AbstractSource;
import com.walterjwhite.email.api.model.Email;
import java.util.Iterator;

public class EmailSource extends AbstractSource<Email, EmailSourceConfiguration> {

  @Override
  protected void doConfigure() {}

  @Override
  public void close() {}

  @Override
  public Iterator<Email> iterator() {
    return null;
  }
}
