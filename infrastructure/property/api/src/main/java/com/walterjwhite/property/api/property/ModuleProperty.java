package com.walterjwhite.property.api.property;

/** Marks a property for injection. */
// this is no longer used since the core injection framework is generalized
// CDI doesn't require the use of "Module" definitions that guice does
// this previously supported injecting properties into modules
@Deprecated
public interface ModuleProperty extends ConfigurableProperty {}
