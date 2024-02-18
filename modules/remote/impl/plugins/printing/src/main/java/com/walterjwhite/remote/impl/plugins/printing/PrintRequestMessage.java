package com.walterjwhite.remote.impl.plugins.printing;

import com.walterjwhite.print.model.PrintRequest;
import com.walterjwhite.remote.api.model.message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class PrintRequestMessage extends Message {
  protected PrintRequest printRequest;
}
