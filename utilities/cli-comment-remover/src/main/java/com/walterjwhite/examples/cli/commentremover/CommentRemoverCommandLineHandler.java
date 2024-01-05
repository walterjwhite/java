package com.walterjwhite.examples.cli.commentremover;

import com.commentremover.app.CommentProcessor;
import com.commentremover.app.CommentRemover;
import com.commentremover.exception.CommentRemoverException;
import com.walterjwhite.examples.cli.commentremover.property.*;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentRemoverCommandLineHandler implements CommandLineHandler {
  protected final Optional<String> absolutePath;
  protected final String relativePath;
  protected final boolean preserveClassHeaders;
  protected final boolean preserveCopyRightHeaders;
  protected final boolean removeJava;
  protected final boolean removeJavaScript;
  protected final boolean removeJSP;
  protected final boolean removeMultiLines;
  protected final boolean removeSingleLines;
  protected final boolean removeTodos;
  protected final Optional<String> excludePackages;

  @Inject
  public CommentRemoverCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(AbsolutePath.class) Optional<String> absolutePath,
      @Property(RelativePath.class) String relativePath,
      @Property(PreserveClassHeaders.class) boolean preserveClassHeaders,
      @Property(PreserveCopyRightHeaders.class) boolean preserveCopyRightHeaders,
      @Property(RemoveJava.class) boolean removeJava,
      @Property(RemoveJavaScript.class) boolean removeJavaScript,
      @Property(RemoveJSP.class) boolean removeJSP,
      @Property(RemoveMultiLines.class) boolean removeMultiLines,
      @Property(RemoveSingleLines.class) boolean removeSingleLines,
      @Property(RemoveTodos.class) boolean removeTodos,
      @Property(ExcludePackages.class) Optional<String> excludePackages) {
    //    super(shutdownTimeoutInSeconds);

    this.absolutePath = absolutePath;
    this.relativePath = relativePath;
    this.preserveClassHeaders = preserveClassHeaders;
    this.preserveCopyRightHeaders = preserveCopyRightHeaders;
    this.removeJava = removeJava;
    this.removeJavaScript = removeJavaScript;
    this.removeJSP = removeJSP;
    this.removeMultiLines = removeMultiLines;
    this.removeSingleLines = removeSingleLines;
    this.removeTodos = removeTodos;
    this.excludePackages = excludePackages;
  }

  @Override
  public void run(String... arguments) throws CommentRemoverException {
    final CommentRemover.CommentRemoverBuilder builder =
        new CommentRemover.CommentRemoverBuilder()
            .removeJava(removeJava)
            .removeJavaScript(removeJavaScript)
            .removeJSP(removeJSP)
            .removeTodos(removeTodos)
            .removeSingleLines(removeSingleLines)
            .removeMultiLines(removeMultiLines)
            .preserveJavaClassHeaders(preserveClassHeaders)
            .preserveCopyRightHeaders(preserveCopyRightHeaders);

    if (absolutePath.isPresent()) {
      builder.startExternalPath(absolutePath.get());
    } else {
      builder.startInternalPath(relativePath);
    }

    if (excludePackages.isPresent()) {
      builder.setExcludePackages(excludePackages.get().split("|"));
    }

    CommentProcessor commentProcessor = new CommentProcessor(builder.build());
    commentProcessor.start();
  }
}
