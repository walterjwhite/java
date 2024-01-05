package com.walterjwhite.amazon.property;

import com.amazonaws.regions.Regions;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

/** Tie the possible values to the enum? */
public interface AmazonRegion extends ConfigurableProperty {
  @DefaultValue Regions Default = Regions.US_EAST_1;
}
