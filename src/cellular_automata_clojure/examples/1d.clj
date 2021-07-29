(ns cellular-automata-clojure.examples.1d
  (:require [cellular-automata-clojure.core :as core]
            [cellular-automata-clojure.rules :refer [rule110 rule30]]
            [cellular-automata-clojure.boards :refer [lonesome-one lonesome-one-short lonesome-one-long]]))


(defn do-run [{:keys [generations rule start]
               :as   argmap}]
  (let [start-state (var-get (ns-resolve 'cellular-automata-clojure.boards start)) ruleset (var-get (ns-resolve 'cellular-automata-clojure.rules rule))]
    (loop [i 0 stop-at generations curr-state start-state]
      (if (= i stop-at)
        curr-state
        (do (println (core/visualize curr-state)) (flush)
            (recur (inc i) stop-at (core/get-next-state curr-state ruleset)))))))


(defn -main 
  ([& args]
   (do-run
    (merge {:generations 10
            :rule 'rule110
            :start 'lonesome-one-short}
           (apply hash-map (map #(if (string? %) (read-string %) %) args))))))