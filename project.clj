(defproject cellular-automata-clojure "0.1.0-SNAPSHOT"
  :description "A Cellular Automata Package written in Clojure"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :main cellular-automata-clojure.core
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :profiles {:dev {:plugins [[com.jakemccrary/lein-test-refresh "0.7.0"]]}}
  :repl-options {:init-ns cellular-automata-clojure.core})
