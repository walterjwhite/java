package com.walterjwhite.examples.upper;

import com.walterjwhite.examples.SPI.SomeInterface;

public class UpperSomeInterface implements SomeInterface {
    @Override
    public String process(final String input) {
        if (input == null) {
            return input;
        }

        return input.toUpperCase();
    }
}
