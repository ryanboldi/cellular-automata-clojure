(ns cellular-automata-clojure.2d.examples
  (:require [cellular-automata-clojure.2d.utils :as utils]))

(defn -main [& args]
  (utils/do-run
   (merge {:generations 10
           :rule 'conways-game-of-life
           :start 'blinker-board}
          (apply hash-map (map #(if (string? %) (read-string %) %) args)))))