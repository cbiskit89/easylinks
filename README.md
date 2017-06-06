# golinks

## Test project to implement a simple URL shortening service.

### Requirements

+ [Bazel](https://bazel.build)
+ [Cassandra DB](http://cassandra.apache.org/)

### Setup

```bash
bazel build //src/main/java/com/example:golinkserver
bazel-bin/src/main/java/com/example/golinkserver

# OR

bazel run //src/main/java/com/example:golinkserver
```

Once built, it is available on http://localhost:8080/

