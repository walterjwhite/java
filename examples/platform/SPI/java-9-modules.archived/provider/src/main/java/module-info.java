module com.walterjwhite.examples.SPI.provider {
  requires com.walterjwhite.examples.SPI;

  provides com.walterjwhite.examples.SPI.SomeInterface with
      com.walterjwhite.examples.SPI.lower.LowerSomeInterface;
}
