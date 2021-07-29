(ns cellular-automata-clojure.core
  (:require [cellular-automata-clojure.rules :as rules]
             [cellular-automata-clojure.boards :as boards]))

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


(defn -main [& args]
  (map #(if (string? %) (read-string %) %) args))

(-main :generations 100 :rule "rule30")