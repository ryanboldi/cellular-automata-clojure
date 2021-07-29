(ns cellular-automata-clojure.boards)

(defn lonesome-one
  "the board is a bunch of zeros on either side with a one in the middle"
  ([length] 
   (if (even? length)
     (lonesome-one (inc length))
   (let [zeros-on-side (- (/ length 2) 1)
         board (concat (repeat zeros-on-side 0) (repeat 1 1) (repeat zeros-on-side 0))]
        (->> board (apply vector)))))
  ([] (lonesome-one 10)))

(def lonesome-one-short (lonesome-one 31))
(def lonesome-one-long (lonesome-one 101))