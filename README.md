# golinks

## Test project to implement a simple URL shortening service.

### Requirements

This project uses the [Bazel](https://bazel.build) build system. 

### Setup

Building and running the project is two simple steps.

```bash
bazel build //src/main/java/com/example:golink
bazel-bin/src/main/java/com/example/golink
```

Once built, it is available on http://localhost:8080/
