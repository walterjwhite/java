package com.walterjwhite.index.service;

import com.walterjwhite.index.api.enumeration.IndexAction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IndexActionMap {
  Index(IndexAction.Index),
  Update(IndexAction.Update),
  Delete(IndexAction.Delete);

  protected final IndexAction indexAction;
}
