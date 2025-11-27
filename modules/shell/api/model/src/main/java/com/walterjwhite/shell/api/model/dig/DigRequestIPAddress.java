package com.walterjwhite.shell.api.model.dig;

import com.walterjwhite.shell.api.model.IPAddress;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class DigRequestIPAddress {

  protected LocalDateTime currentDateTime = LocalDateTime.now();

  protected IPAddress ipAddress;

  protected DigRequest digRequest;
}
