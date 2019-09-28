load("@com_github_bazelbuild_buildtools//buildifier:def.bzl", "buildifier")

buildifier(
    name = "buildifier",
)

load("@io_bazel_rules_go//go:def.bzl", "go_binary", "go_library")

# Package rules for URL Shortener.

load("@bazel_gazelle//:def.bzl", "gazelle")

# gazelle:prefix github.com/cbiskit89/golinks
gazelle(name = "gazelle")

go_library(
    name = "go_default_library",
    srcs = ["server.go"],
    data = glob(["templates/*.html"]),
    importpath = "github.com/cbiskit89/golinks",
    visibility = ["//visibility:private"],
)

go_binary(
    name = "golinks",
    embed = [":go_default_library"],
    visibility = ["//visibility:public"],
)
