## Goals
1. eliminate or drastically reduce logger usage
2. properly scope methods to ensure accurate logging
3. allow code to focus on one thing while cross-cutting concerns can be handled separately

### Conventions
1. log level is determined by method visibility (AND if required, the Loggable annotation)
2. setters and getters will be captured at TRACE, AND avoid method names with void set*, get*, or boolean is*
3. public methods will be info
4. protected methods will be debug

### Contextual Logging
In order to take utilize contextual logging, a class must either implement ContextualLoggable or FieldContextualLoggable.
For FieldContextualLoggable, any field annotated with ContextualLoggableField will be logged.
Sensitive values should not be annotated.

### Examples
1. examples/infrastructure/advice/byte-buddy
	intercepts ByteBuddyFoo.anotherMethod with MethodInterceptor.intercept
2. examples/infrastructure/advice/heartbeat
	while 10 concurrent sleep operations run, a heartbeat is printed
	log output illustrates conventions, public -> info, protected -> debug, constructors, setters, getters -> trace

## Byte Buddy Implementation
### Known Issues / Limitations
1. reintroduce limiting the number of arguments logged, for some method calls that take a list, the list could be huge and logging this would just eat up space, does that really provide value?
2. number of arguments logged is set statically
