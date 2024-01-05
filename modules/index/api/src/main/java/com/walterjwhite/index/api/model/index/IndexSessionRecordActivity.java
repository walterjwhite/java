package com.walterjwhite.index.api.model.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.index.api.enumeration.IndexAction;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class IndexSessionRecordActivity extends AbstractEntity {

  protected IndexAction indexAction;
}
