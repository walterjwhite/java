package com.walterjwhite.queue.api.job;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.queue.api.enumeration.ExecutionState;
import com.walterjwhite.queue.api.enumeration.QueueState;
import com.walterjwhite.queue.api.model.AbstractQueued;
import com.walterjwhite.queue.api.model.JobExecution;
import com.walterjwhite.queue.api.model.QueuedJob;
import com.walterjwhite.queue.api.service.JobWorkerService;
import com.walterjwhite.queue.api.service.QueueService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Data
@ToString(doNotUseGetters = true)
@RequiredArgsConstructor
public abstract class AbstractRunnable<QueuedType extends AbstractQueued>
        implements Runnable, Heartbeatable, AutoCloseable {
    protected Duration heartbeatInterval;
    protected Duration interruptGracePeriodTimeout;

    protected transient Thread executingThread;

    protected QueuedType queued;
    protected JobExecution currentJobExecution;
    protected JobWorkerService jobWorkerService;
    protected RunningFuture runningFuture;

    public void run() {
        executingThread = Thread.currentThread();

        startCurrentJobExecution();
        updateJobExecutionStatus(ExecutionState.Running);

        try {
            doCall();
            onSuccess();
        } catch (Exception e) {
            onError(e);
        }
    }

    protected void startCurrentJobExecution() {
        currentJobExecution = new JobExecution(queued);
        queued.setCurrentJobExecution(currentJobExecution);
        queued.getJobExecutions().add(currentJobExecution);
    }

    @Transactional
    protected void updateJobExecutionStatus(ExecutionState jobState) {
        currentJobExecution.setExecutionState(jobState);
        currentJobExecution.setUpdateDateTime(LocalDateTime.now());

        
        ApplicationHelper.getApplicationInstance()
                .getInjector()
                .getInstance(QueueService.class)
                .update(queued);
    }

    
    @Heartbeat
    protected abstract void doCall() throws Exception;





    protected void onSuccess() {
        jobWorkerService.remove(runningFuture);

        updateJobExecutionStatus(ExecutionState.Completed);
        updateJobStatus();

        doOnSuccess();
    }

    @Transactional
    protected void updateJobStatus() {

        if (((QueuedJob) queued).getScheduleInstance().isRecurring()) {
            queued.setQueueState(QueueState.Completed);
        }
    }

    protected void doOnSuccess() {
    }

    protected void onError(Exception e) {
        LOGGER.warn("Error", e);

        jobWorkerService.remove(runningFuture);

        currentJobExecution.setThrowable(e);
        updateJobExecutionStatus(ExecutionState.Exception);

        retry(e);
    }

    
    @Transactional
    public void onHeartbeat() {
        if (ApplicationHelper.getApplicationInstance()
                .getInjector()
                .getInstance(QueueService.class)
                .wasCancelled(queued)) {

            close();
            return;
        }


        updateJobExecutionStatus(currentJobExecution.getExecutionState());
    }

    protected void retry(Exception e) {
        if (isRetryable(e)) {

            return;
        }
    }

    protected boolean isRetryable(Throwable t) {
        return t instanceof RuntimeException;
    }


    @Override
    public void close() {
        try {
            if (executingThread.isAlive()) {
                executingThread.interrupt();
            }
        } catch (Exception e) {
            LOGGER.warn("Interrupted", e);
        }
    }
}
