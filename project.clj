(defproject framework "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure      "1.10.1"]
                 [metosin/muuntaja         "0.6.8"]
                 [metosin/reitit           "0.5.15"]
                 [metosin/reitit-dev       "0.5.15"]
                 [ring/ring-core           "1.9.4"]
                 [ring/ring-jetty-adapter  "1.9.4"]]
  :repl-options {:init-ns framework.core}
  :main framework.core)
