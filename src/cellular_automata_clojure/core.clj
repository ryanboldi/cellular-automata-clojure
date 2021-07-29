(ns cellular-automata-clojure.core
  (:require [cellular-automata-clojure.rules :as rules]))

(defn apply-rule
  "applies ruleset to length 3 vector (vec)"
  [vec ruleset]
  (ruleset vec))

(defn split-up [input]
  (into 
   [[(last input) (first input) (second input)]]
   (map #(apply vector %) (partition 3 1 input input))))

(defn get-next-state
  [input rule]
  (->> input
       (split-up)
       (map #(apply-rule (apply vector %) rule))
       (apply vector)))

(defn visualize [input]
  (->> input
       (map #(if (zero? %) "." "#"))
       (apply str)))

(defn -main [generations]
  (loop [i 0 stop-at generations curr-state [0 0 0 1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1]]
    (if (= i stop-at)
      curr-state
      (do (println (visualize curr-state)) (flush)
          (recur (inc i) stop-at (get-next-state curr-state rules/rule30))))))

(-main 10)