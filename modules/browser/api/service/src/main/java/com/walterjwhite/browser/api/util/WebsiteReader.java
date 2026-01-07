package com.walterjwhite.browser.api.util;

import com.walterjwhite.browser.api.authentication.AuthenticatedWebSession;
import com.walterjwhite.browser.api.model.Website;
import com.walterjwhite.file.modules.resources.JarReadUtils;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class WebsiteReader {
  protected final SerializationService serializationService;

  public Website read(final String websiteIdentifier) throws IOException {
    try (final InputStream inputStream =
        JarReadUtils.getFileFromResourceAsStream(
            "website/authentication/" + websiteIdentifier + ".yaml")) {
      return serializationService.deserialize(inputStream, Website.class);
    }
  }

  public void initWebsite(final AuthenticatedWebSession webSession) throws IOException {
    webSession.getWebCredentials().setWebsite(read(webSession.getWebsiteIdentifier()));
  }
}
