package com.walterjwhite.property.api.property;

import com.walterjwhite.property.api.annotation.Optional;
import com.walterjwhite.property.api.annotation.PropertyValueType;

@PropertyValueType(int.class)
@Optional
public interface ProxyPort extends ConfigurableProperty {}
