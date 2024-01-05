package com.walterjwhite.property.api.property;

import com.walterjwhite.property.api.annotation.Optional;

/**
 * Default value was removed, instead system relies on environmental variable to be set OR,
 * command-line option, or properties file.
 */
@Optional
public interface ProxyHost extends ConfigurableProperty {}
