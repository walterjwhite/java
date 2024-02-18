module com.walterjwhite.examples.lower {
  requires com.walterjwhite.examples.SPI;

  provides com.walterjwhite.examples.SPI.SomeInterface with
      com.walterjwhite.examples.lower.LowerSomeInterface;
}
