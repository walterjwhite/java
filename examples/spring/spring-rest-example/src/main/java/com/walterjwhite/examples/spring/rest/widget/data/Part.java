package com.walterjwhite.examples.spring.rest.widget.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part extends PartIdentifier {
    protected Assembly assembly;
}
