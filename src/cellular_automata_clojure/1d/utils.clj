(ns cellular-automata-clojure.1d.utils)

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