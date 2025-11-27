package com.walterjwhite.remote.api.model.message;

import java.util.concurrent.TimeUnit;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class BeQuietMessage extends Message {

  protected long duration;

  protected TimeUnit timeUnit;
}
