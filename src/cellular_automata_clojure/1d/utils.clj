(ns cellular-automata-clojure.1d.utils
  (:require [cellular-automata-clojure.1d.boards]
            [cellular-automata-clojure.1d.rules]))

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
       (map #(if (zero? %) "  " " #"))
       (apply str)))

(defn do-run [{:keys [generations rule start]
               :as   argmap}]
  (let [start-state (var-get (ns-resolve 'cellular-automata-clojure.1d.boards start)) ruleset (var-get (ns-resolve 'cellular-automata-clojure.1d.rules rule))]
    (loop [i 0 curr-state start-state]
      (Thread/sleep 50)
      (if (= i generations)
        curr-state
        (do (println (visualize curr-state)) (flush)
            (recur (inc i) (get-next-state curr-state ruleset)))))))