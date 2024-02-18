package com.walterjwhite.ssh.api.service;

import java.io.File;


public interface PrintService {
   void print(final File file );

   void print(final String uri );
}
