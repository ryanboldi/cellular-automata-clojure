(ns cellular-automata-clojure.1d.examples
  (:require [cellular-automata-clojure.1d.utils :as utils]
            [cellular-automata-clojure.1d.boards]
            [cellular-automata-clojure.1d.rules]))


(defn do-run [{:keys [generations rule start]
               :as   argmap}]
  (let [start-state (var-get (ns-resolve 'cellular-automata-clojure.1d.boards start)) ruleset (var-get (ns-resolve 'cellular-automata-clojure.1d.rules rule))]
    (loop [i 0 stop-at generations curr-state start-state]
      (Thread/sleep 50)
      (if (= i stop-at)
        curr-state
        (do (println (utils/visualize curr-state)) (flush)
            (recur (inc i) stop-at (utils/get-next-state curr-state ruleset)))))))

(defn -main 
  ([& args]
   (do-run
    (merge {:generations 10
            :rule 'rule110
            :start 'lonesome-one-short}
           (apply hash-map (map #(if (string? %) (read-string %) %) args))))))

(-main)