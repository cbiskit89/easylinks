load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
http_archive(
    name = "io_bazel_rules_go",
    urls = ["https://github.com/bazelbuild/rules_go/releases/download/0.15.4/rules_go-0.15.4.tar.gz"],
    sha256 = "7519e9e1c716ae3c05bd2d984a42c3b02e690c5df728dc0a84b23f90c355c5a1",
)
http_archive(
    name = "bazel_gazelle",
    urls = ["https://github.com/bazelbuild/bazel-gazelle/releases/download/0.14.0/bazel-gazelle-0.14.0.tar.gz"],
    sha256 = "c0a5739d12c6d05b6c1ad56f2200cb0b57c5a70e03ebd2f7b87ce88cabf09c7b",
)

load("@io_bazel_rules_go//go:def.bzl", "go_rules_dependencies", "go_register_toolchains")
go_rules_dependencies()
go_register_toolchains()
load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")
gazelle_dependencies()

maven_jar(
    name = "com_codahale_metrics_metrics_core",
    artifact = "com.codahale.metrics:metrics-core:3.0.2",
    sha1 = "c6a7fb32776e984b64ff1a548e3044238ea5a931",
)

maven_jar(
    name = "com_datastax_cassandra_cassandra_driver_core",
    artifact = "com.datastax.cassandra:cassandra-driver-core:3.2.0",
    sha1 = "065ffab45202c0830a37140dbc22c021b8a269c0",
)

maven_jar(
    name = "com_datastax_cassandra_cassandra_driver_mapping",
    artifact = "com.datastax.cassandra:cassandra-driver-mapping:3.2.0",
    #sha1 = "065ffab45202c0830a37140dbc22c021b8a269c0",
)

maven_jar(
    name = "com_datastax_cassandra_cassandra_driver_extras",
    artifact = "com.datastax.cassandra:cassandra-driver-extras:3.2.0",
    #sha1 = "065ffab45202c0830a37140dbc22c021b8a269c0",
)

maven_jar(
    name = "com_google_guava_guava",
    artifact = "com.google.guava:guava:22.0",
    sha1 = "3564ef3803de51fb0530a8377ec6100b33b0d073",
)

maven_jar(
    name = "io_netty_netty_all",
    artifact = "io.netty:netty-all:4.1.9.Final",
    sha1 = "0097860965d6a0a6b98e7f569f3f966727b8db75",
)

maven_jar(
    name = "javax_servlet_javax_servlet_api",
    artifact = "javax.servlet:javax.servlet-api:4.0.0-b06",
    sha1 = "663b9d3ac21fced6b7f87820971c1f8678a32e86",
)

maven_jar(
    name = "org_eclipse_jetty_jetty_http",
    artifact = "org.eclipse.jetty:jetty-http:9.4.5.v20170502",
    sha1 = "c51b8a6a67d64672889249dd958edd77bff8fc0c",
)

maven_jar(
    name = "org_eclipse_jetty_jetty_io",
    artifact = "org.eclipse.jetty:jetty-io:9.4.5.v20170502",
    sha1 = "76086f955d4e943396b8f340fd5bae3ce4da19d9",
)

maven_jar(
    name = "org_eclipse_jetty_jetty_security",
    artifact = "org.eclipse.jetty:jetty-security:9.4.5.v20170502",
    sha1 = "4f4fc4cbe3504b6c91143ee37b38a1f3de2dcc72"
)

maven_jar(
    name = "org_eclipse_jetty_jetty_server",
    artifact = "org.eclipse.jetty:jetty-server:9.4.5.v20170502",
    sha1 = "b4d30340213c3d2a5f908860ba170c5a697829be",
)

maven_jar(
    name = "org_eclipse_jetty_jetty_servlet",
    artifact = "org.eclipse.jetty:jetty-servlet:9.4.5.v20170502",
    #sha1 = "b4d30340213c3d2a5f908860ba170c5a697829be",
)

maven_jar(
    name = "org_eclipse_jetty_jetty_util",
    artifact = "org.eclipse.jetty:jetty-util:9.4.5.v20170502",
    sha1 = "5fd36dfcf39110b809bd9b20cec62706ab694711",
)

maven_jar(
    name = "org_slf4j_slf4j_api",
    artifact = "org.slf4j:slf4j-api:1.7.9",
    sha1 = "872ec63b41181e29ad5a3723f1417356e2d2c0f2",
)

