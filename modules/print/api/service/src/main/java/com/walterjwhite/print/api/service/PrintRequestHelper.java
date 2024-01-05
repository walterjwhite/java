package com.walterjwhite.print.api.service;

import com.walterjwhite.file.api.model.File;
import com.walterjwhite.print.enumeration.PrinterType;
import com.walterjwhite.print.model.Location;
import com.walterjwhite.print.model.PrintRequest;

// NOTE: assume the user has already uploaded the file to the file storage service provider
// new
// PrintRequestHelper.Builder().withFile(null).withLocation(null).withPassword(null).withPrinterType(null);
public class PrintRequestHelper {
  //    protected final FileStorageService fileStorageService;

  //    @Inject
  //    public PrintRequestHelper(FileStorageService fileStorageService) {
  //        this.fileStorageService = fileStorageService;
  //    }

  public static class Builder {
    private final PrintRequest printRequest = new PrintRequest();

    public Builder withFile(File file) {
      printRequest.setFile(file);
      //            fileStorageService.put(file);
      return this;
    }

    public Builder withLocation(Location location) {
      printRequest.setLocation(location);
      return this;
    }

    public Builder withPrinterType(PrinterType printerType) {
      printRequest.setPrinterType(printerType);
      return this;
    }

    public Builder withPassword(String password) {
      printRequest.setPassword(password);
      return this;
    }
  }
}
