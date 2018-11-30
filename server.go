package main

import (
    "fmt"
    "log"
    "net/http"
    "text/template"
)

func MainHandler(w http.ResponseWriter, req *http.Request) {
    template, err := template.ParseFiles("templates/main.html")

    if err != nil {
        fmt.Fprintf(w, "oops")
    }

    template.Execute(w, nil)
}

func CreateHandler(w http.ResponseWriter, req *http.Request) {
    template, err := template.ParseFiles("templates/create.html")

    if err != nil {
        fmt.Fprintf(w, "oops")
    }

    template.Execute(w, nil)
}

func main() {
    mux := http.NewServeMux()
    mux.HandleFunc("/", MainHandler)
    mux.HandleFunc("/create", CreateHandler)
    s := &http.Server{
        Addr: ":8080",
        Handler: mux,
    }
    log.Fatal(s.ListenAndServe())
}
