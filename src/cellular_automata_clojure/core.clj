(ns cellular-automata-clojure.core
  (:require [cellular-automata-clojure.rules :as rules]))

(defn apply-rule
  "applies ruleset to length 3 vector (vec)"
  [vec ruleset]
  (ruleset vec))


(defn split-up [input]
  (into [[(last input) (first input) (second input)]] (map #(apply vector %) (partition 3 1 input input))))

(defn get-next-state
  [input rule]
  (->> input
       (split-up)
       (map #(apply-rule (apply vector %) rule))
       (apply vector)))

(defn -main [& args]
  (apply-rule [1 1 1] rules/rule30))

(apply-rule (vector 1 1 1) rules/rule30)

(get-next-state [1 1 1 0 0 0 1 1 1 0 0 0] rules/rule30)

(partition 3 1 [1 1 1 0 0 0 1 1 1 0 0 0] [1 1 1 0 0 0 1 1 1 0 0 0])
