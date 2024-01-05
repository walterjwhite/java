package com.walterjwhite.file.modules.watcher;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * A simple class which can monitor files and notify interested parties (i.e. listeners) of file
 * changes.
 *
 * <p>This class is kept lean by only keeping methods that are actually being called.
 */
public class SimpleDirectoryWatcherService implements DirectoryWatcherService, Runnable {
  private final WatchService mWatchService;
  private final AtomicBoolean mIsRunning;
  private final ConcurrentMap<WatchKey, Path> mWatchKeyToDirPathMap;
  private final ConcurrentMap<Path, Set<OnFileChangeListener>> mDirPathToListenersMap;
  private final ConcurrentMap<OnFileChangeListener, Set<PathMatcher>> mListenerToFilePatternsMap;

  /**
   * A simple no argument constructor for creating a <code>SimpleDirectoryWatchService</code>.
   *
   * @throws IOException If an I/O error occurs.
   */
  public SimpleDirectoryWatcherService() throws IOException {
    mWatchService = FileSystems.getDefault().newWatchService();
    mIsRunning = new AtomicBoolean(false);
    mWatchKeyToDirPathMap = newConcurrentMap();
    mDirPathToListenersMap = newConcurrentMap();
    mListenerToFilePatternsMap = newConcurrentMap();
  }

  @SuppressWarnings("unchecked")
  private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
    return (WatchEvent<T>) event;
  }

  private static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
    return new ConcurrentHashMap<>();
  }

  private static <T> Set<T> newConcurrentSet() {
    return Collections.newSetFromMap(newConcurrentMap());
  }

  public static PathMatcher matcherForGlobExpression(String globPattern) {
    return FileSystems.getDefault().getPathMatcher("glob:" + globPattern);
  }

  public static boolean matches(Path input, PathMatcher pattern) {
    return pattern.matches(input);
  }

  public static boolean matchesAny(Path input, Set<PathMatcher> patterns) {
    for (PathMatcher pattern : patterns) {
      if (matches(input, pattern)) {
        return true;
      }
    }

    return false;
  }

  private Path getDirPath(WatchKey key) {
    return mWatchKeyToDirPathMap.get(key);
  }

  private Set<OnFileChangeListener> getListeners(Path dir) {
    return mDirPathToListenersMap.get(dir);
  }

  private Set<PathMatcher> getPatterns(OnFileChangeListener listener) {
    return mListenerToFilePatternsMap.get(listener);
  }

  private Set<OnFileChangeListener> matchedListeners(Path dir, Path file) {
    return getListeners(dir).stream()
        .filter(listener -> matchesAny(file, getPatterns(listener)))
        .collect(Collectors.toSet());
  }

  private void notifyListeners(WatchKey key) {
    for (WatchEvent<?> event : key.pollEvents()) {
      WatchEvent.Kind eventKind = event.kind();

      // Overflow occurs when the watch event queue is overflown
      // with events.
      if (eventKind.equals(OVERFLOW)) {
        return;
      }

      WatchEvent<Path> pathEvent = cast(event);
      Path file = pathEvent.context();

      if (eventKind.equals(ENTRY_CREATE)) {
        matchedListeners(getDirPath(key), file)
            .forEach(listener -> listener.onFileCreate(file.toString()));
      } else if (eventKind.equals(ENTRY_MODIFY)) {
        matchedListeners(getDirPath(key), file)
            .forEach(listener -> listener.onFileModify(file.toString()));
      } else if (eventKind.equals(ENTRY_DELETE)) {
        matchedListeners(getDirPath(key), file)
            .forEach(listener -> listener.onFileDelete(file.toString()));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void register(OnFileChangeListener listener, String dirPath, String... globPatterns)
      throws IOException {
    Path dir = Paths.get(dirPath);

    if (!Files.isDirectory(dir)) {
      throw new IllegalArgumentException(dirPath + " is not a directory.");
    }

    if (!mDirPathToListenersMap.containsKey(dir)) {
      // May throw
      WatchKey key = dir.register(mWatchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

      mWatchKeyToDirPathMap.put(key, dir);
      mDirPathToListenersMap.put(dir, newConcurrentSet());
    }

    getListeners(dir).add(listener);

    Set<PathMatcher> patterns = newConcurrentSet();

    for (String globPattern : globPatterns) {
      patterns.add(matcherForGlobExpression(globPattern));
    }

    if (patterns.isEmpty()) {
      patterns.add(matcherForGlobExpression("*")); // Match everything if no filter is found
    }

    mListenerToFilePatternsMap.put(listener, patterns);
  }

  /**
   * Start this <code>SimpleDirectoryWatchService</code> instance by spawning a new thread.
   *
   * <p>see: #stop()
   */
  @Override
  public void start() {
    if (mIsRunning.compareAndSet(false, true)) {
      Thread runnerThread = new Thread(this, DirectoryWatcherService.class.getSimpleName());
      runnerThread.start();
    }
  }

  /**
   * Stop this <code>SimpleDirectoryWatchService</code> thread. The killing happens lazily, giving
   * the running thread an opportunity to finish the work at hand.
   *
   * <p>see: #start()
   */
  @Override
  public void stop() {
    mIsRunning.set(false);
  }

  /** {@inheritDoc} */
  @Override
  public void run() {
    while (mIsRunning.get() && doRunIteration()) {}

    stop();
  }

  protected boolean doRunIteration() {
    WatchKey key;
    try {
      key = mWatchService.take();
    } catch (InterruptedException e) {
      return false;
    }

    if (!isWatchKeyRecognized(key)) {
      return true;
    }

    notifyListeners(key);
    return resetKey(key);
  }

  protected boolean resetKey(WatchKey watchKey) {
    // Reset key to allow further events for this key to be processed.
    boolean valid = watchKey.reset();
    if (!valid) {
      mWatchKeyToDirPathMap.remove(watchKey);
      return !mWatchKeyToDirPathMap.isEmpty();
    }

    return true;
  }

  protected boolean isWatchKeyRecognized(final WatchKey watchKey) {
    return getDirPath(watchKey) != null;
  }
}
