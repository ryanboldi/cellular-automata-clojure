(ns cellular-automata-clojure.1d.examples
  (:require [cellular-automata-clojure.1d.utils :as utils]))

(defn -main 
  ([& args]
   (utils/do-run
    (merge {:generations 10
            :rule 'rule110
            :start 'lonesome-one-short}
           (apply hash-map (map #(if (string? %) (read-string %) %) args))))))