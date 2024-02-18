package com.walterjwhite.remote.api.model.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)


@PersistenceCapable
public class BeQuietMessage extends Message {

  protected long duration;

  protected TimeUnit timeUnit;
}
