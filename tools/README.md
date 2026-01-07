This folder contains helper scripts related to Copilot-generated tests.

generate_tests_for_all_modules.sh
- Writes a safe, reflective JUnit 5 test into every Maven module that has
  `src/main/java`. The test attempts to load classes (without initializing)
  to catch package/name mismatches and compilation/load-time issues.
- Attempts to run `mvn -pl <module> test` for each module and commits the
  generated tests with the message `copilot - add tests` if tests pass.

Usage
Run from the repository root:

```bash
./tools/generate_tests_for_all_modules.sh
```

Note: the script requires Maven and Java on PATH to execute tests. If Maven
isn't available in your environment, the script will only generate the tests
and stage them for commit; you can run the tests locally and commit.

