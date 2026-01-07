package com.walterjwhite.examples.cli.commentremover;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommentRemoverOperatingModeAutoTest {

  @Test
  void defaultHasInitiatorClass() {
    CommentRemoverOperatingMode mode = CommentRemoverOperatingMode.Default;
    assertNotNull(mode.getInitiatorClass());
  }
}
