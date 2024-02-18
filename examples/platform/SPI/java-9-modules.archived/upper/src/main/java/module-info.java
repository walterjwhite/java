module com.walterjwhite.examples.upper {
  requires com.walterjwhite.examples.SPI;

  provides com.walterjwhite.examples.SPI.SomeInterface with
      com.walterjwhite.examples.upper.UpperSomeInterface;
}
