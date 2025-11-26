package com.walterjwhite.examples.spring.rest.widget.data;

import lombok.Data;

@Data
public abstract class PartIdentifier {
    protected String id;
    protected String name;
    protected String description;
}
