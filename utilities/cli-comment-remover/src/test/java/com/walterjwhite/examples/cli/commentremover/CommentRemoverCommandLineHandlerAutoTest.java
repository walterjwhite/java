package com.walterjwhite.examples.cli.commentremover;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommentRemoverCommandLineHandlerAutoTest {

  @Test
  void constructorSetsFields() {
    CommentRemoverCommandLineHandler handler =
        new CommentRemoverCommandLineHandler(
            ".", false, false, false, false, false, false, false, false, "");

    assertEquals(".", handler.path);
    assertFalse(handler.preserveClassHeaders);
    assertFalse(handler.preserveCopyRightHeaders);
    assertFalse(handler.removeJava);
    assertFalse(handler.removeJavaScript);
    assertFalse(handler.removeJSP);
    assertFalse(handler.removeMultiLines);
    assertFalse(handler.removeSingleLines);
    assertFalse(handler.removeTodos);
    assertEquals("", handler.excludePackages);
  }
}
