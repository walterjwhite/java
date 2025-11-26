package com.walterjwhite.index.api.model.index;

import com.walterjwhite.index.api.enumeration.IndexAction;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class IndexSessionRecordActivity {

  protected IndexAction indexAction;
}
