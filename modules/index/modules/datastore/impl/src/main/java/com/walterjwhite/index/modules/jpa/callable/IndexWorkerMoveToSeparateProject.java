package com.walterjwhite.index.modules.jpa.callable;

public class IndexWorkerMoveToSeparateProject /*extends AbstractCommandLineHandler */ {
  // 1. register daemon
  // 2. include executor which has the responsibility of registering the jobWorkerService with the
  // database
  // 3. jobWorkerService periodically checks for new jobs and puts them into the scheduler
  // 4. if another workers picks up the job and updates the DB before we do, we throw an optimistic
  // lock exception
  // 5. we work on the task, then mark it as complete when done
  // 6. task queueing merely puts the job into the database, the scheduler code doesn't live there
  // at all
}
