package com.walterjwhite.data.pipe.api;

public interface Pipe {
  void pipe(DataPipeConfiguration dataPipeConfiguration) throws Exception;

  void start();

  void close();
}
