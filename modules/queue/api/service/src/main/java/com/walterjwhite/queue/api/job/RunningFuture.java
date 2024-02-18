package com.walterjwhite.queue.api.job;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Future;

@Getter
@RequiredArgsConstructor
public class RunningFuture {
    protected final Future future;
    protected final int queuedId;
    protected final int executionId;
    protected final int runnableId;
}
