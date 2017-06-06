package(default_visibility = ["//visibility:public"])

java_binary(
    name = "golinkserver",
    srcs = ["GoLinkServer.java"],
    main_class = "com.example.GoLinkServer",
    deps = [
        ":handlers_lib",
        "@org_eclipse_jetty_jetty_server//jar",
    ],
)

java_library(
    name = "handlers_lib",
    srcs = glob(["handlers/*.java"]),
    deps = [
        "@com_codahale_metrics_metrics_core//jar",
        "@com_datastax_cassandra_cassandra_driver_core//jar",
        "@com_google_guava_guava//jar",
	"@javax_servlet_javax_servlet_api//jar",
        "@io_netty_netty_all//jar",
        "@org_eclipse_jetty_jetty_http//jar",
        "@org_eclipse_jetty_jetty_io//jar",
        "@org_eclipse_jetty_jetty_server//jar",
	"@org_eclipse_jetty_jetty_util//jar",
        "@org_slf4j_slf4j_api//jar",
    ],
)

