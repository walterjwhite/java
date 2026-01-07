package com.walterjwhite.citrix;

import java.io.IOException;

public interface CitrixService {
  void run(final CitrixSession citrixSession, final AbstractCitrixExtension citrixExtension)
      throws Exception;

  <Type> Type launch(final CitrixSession citrixSession, final int index) throws IOException;

}
