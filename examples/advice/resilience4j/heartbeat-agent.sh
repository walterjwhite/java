#!/bin/sh

java21 -javaagent:../../../advice/heartbeat/agent/target/agent-shaded.jar -jar target/resilience4j-*.jar >.log 2>&1
