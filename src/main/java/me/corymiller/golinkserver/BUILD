package(default_visibility = ["//visibility:private"])

java_binary(
    name = "golinkserver",
    srcs = ["GoLinkServer.java"],
    main_class = "me.corymiller.golinkserver.GoLinkServer",
    deps = [
        ":servlets_lib",
        "@io_netty_netty_all//jar",
        "@org_eclipse_jetty_jetty_http//jar",
        "@org_eclipse_jetty_jetty_io//jar",
        "@org_eclipse_jetty_jetty_security//jar",
        "@org_eclipse_jetty_jetty_server//jar",
        "@org_eclipse_jetty_jetty_servlet//jar",
	"@org_eclipse_jetty_jetty_util//jar",
        "@org_slf4j_slf4j_api//jar",
    ],
)

java_library(
    name = "servlets_lib",
    srcs = glob(["servlets/*.java"]),
    deps = [
        ":utils_lib",
        "@com_codahale_metrics_metrics_core//jar",
        "@com_datastax_cassandra_cassandra_driver_core//jar",
        "@com_google_guava_guava//jar",
	"@javax_servlet_javax_servlet_api//jar",
    ],
)

java_library(
    name = "utils_lib",
    srcs = glob(["utils/*.java"]),
    deps = [],
)
