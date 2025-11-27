package com.walterjwhite.citrix;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCitrixExtension implements Runnable {
    protected final CitrixSession citrixSession;
    protected final int iterationDelay;
    protected final int iterationCount;

    @Getter
    @Setter
    protected Object data;

    @SneakyThrows
    @Override
    public void run() {
        int i = 0;
        for (; i == 0 || i < iterationCount; i++) {
            doRun();

            if (i + 1 < iterationCount) {
                Thread.sleep(iterationDelay);
            }
        }

        LOGGER.warn("finished execution after: {} invocations", i);
    }

    protected abstract void doRun();
}
