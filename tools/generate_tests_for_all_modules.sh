#!/usr/bin/env bash
set -euo pipefail

#

ROOT_DIR=$(pwd)
GIT_COMMIT_MSG="copilot - add tests"

echo "Scanning for Maven modules with src/main/java..."
mapfile -t modules < <(find . -type d -name java -path "*/src/main/java" -prune -print | sed -e 's|/src/main/java||' | sort -u)

echo "Found ${#modules[@]} modules"

read -r -d '' TEST_TEMPLATE <<'EOF'
package org.copilot.tests;

import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.util.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class AllClassesLoadTest {

  @Test
  void loadAllClassesWithoutInitializing() throws Exception {
    Path src = Paths.get("src/main/java");
    if (!Files.exists(src)) {
      // nothing to test in this module
      return;
    }

    List<String> classNames = new ArrayList<>();
    Files.walk(src)
        .filter(p -> p.toString().endsWith(".java"))
        .filter(p -> !p.getFileName().toString().equals("module-info.java"))
        .forEach(p -> {
          Path rel = src.relativize(p);
          String fq = rel.toString().replace(File.separatorChar, '.')
              .replaceAll("\\.java$", "");
          classNames.add(fq);
        });

    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    for (String name : classNames) {
      try {
        Class.forName(name, false, cl);
      } catch (ClassNotFoundException cnfe) {
        fail("Class not found: " + name + " (ensure package matches path)");
      }
    }
  }
}
EOF

for mod in "${modules[@]}"; do
	if [ ! -d "$mod/src/main/java" ]; then
		continue
	fi
	test_dir="$mod/src/test/java/org/copilot/tests"
	test_file="$test_dir/AllClassesLoadTest.java"
	mkdir -p "$test_dir"
	echo "Writing test to $test_file"
	printf "%s\n" "$TEST_TEMPLATE" >"$test_file"

done

echo "Staging generated tests..."
git add -A


if ! command -v mvn >/dev/null 2>&1; then
	echo "mvn not found in PATH — generated tests are written but cannot be executed here."
	echo "Run the script locally with Maven available to run tests and commit changes."
	exit 0
fi

run_module_tests() {
	local module_path="$1"
	echo "Running mvn -pl $module_path test"
	mvn -q -DskipTests=false -pl "$module_path" test
}

failed_modules=()
for mod in "${modules[@]}"; do
	if [ ! -d "$mod" ]; then
		continue
	fi
	echo "\n=== Testing module: $mod ==="
	set +e
	mvn -q -pl "$mod" test
	rc=$?
	set -e
	if [ $rc -ne 0 ]; then
		echo "Tests failed for $mod"
		failed_modules+=("$mod")
	else
		echo "Tests passed for $mod"
	fi
done

if [ ${#failed_modules[@]} -eq 0 ]; then
	echo "All module tests passed (or were not runnable). Committing tests."
	git commit -m "$GIT_COMMIT_MSG" || echo "Nothing to commit"
	exit 0
fi

echo "The following modules had test failures:"
printf '%s\n' "${failed_modules[@]}"

git commit -m "$GIT_COMMIT_MSG" || echo "Nothing to commit"

exit 0
