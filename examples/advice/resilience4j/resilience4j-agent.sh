#!/bin/sh

java -javaagent:../../../advice/resilience4j/agent/target/agent-shaded.jar -jar target/resilience4j-*.jar >.log 2>&1
