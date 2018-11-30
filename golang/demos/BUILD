load("@io_bazel_rules_go//go:def.bzl", "go_binary", "go_library")

go_library(
    name = "go_default_library",
    srcs = ["helloworld.go"],
    importpath = "github.com/cbiskit89/golinks/golang/demos",
    visibility = ["//visibility:private"],
)

go_binary(
    name = "demos",
    embed = [":go_default_library"],
    visibility = ["//visibility:public"],
)
