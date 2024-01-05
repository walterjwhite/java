package com.walterjwhite.browser.data;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.file.api.model.File;
import java.time.LocalDateTime;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class Screenshot extends AbstractEntity {
  @EqualsAndHashCode.Exclude protected File file;

  protected BrowserSessionResourceURI browserSessionResourceURI;

  protected LocalDateTime screenshotDateTime = LocalDateTime.now();

  public Screenshot(File file, BrowserSessionResourceURI browserSessionResourceURI) {

    this.file = file;
    this.browserSessionResourceURI = browserSessionResourceURI;
  }
}
