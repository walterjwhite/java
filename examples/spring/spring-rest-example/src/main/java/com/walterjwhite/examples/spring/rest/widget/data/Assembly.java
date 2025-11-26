package com.walterjwhite.examples.spring.rest.widget.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assembly extends PartIdentifier {
    protected List<Part> parts;
}
