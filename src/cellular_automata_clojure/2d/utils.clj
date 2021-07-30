(ns cellular-automata-clojure.2d.utils)

(defn random-board [] (apply vector (repeatedly 5 #(rand-int 2))))
(def board (apply vector (repeatedly 5 random-board)))

board
(flatten board)
(partition 3 (flatten board))
(partition 3 3 (partition 3 (flatten board)) (partition 3 3 (flatten board) (flatten board)))
(apply mapv vector (partition 3 (flatten board)))

(defn ind-to-xy
 "takes a flattened index and turns it into an xy position for a square board"
  [index board]
  (let [width (count board)]
    (loop [rem index x 0 y 0]
      (if (= rem 0)
        {:x x :y y}
        (if (> rem width)
          (recur (- rem width) x (inc y))
          (recur (dec rem) (inc x) y))))))

(defn xy-to-ind [{:keys [:x :y]} board]
  (let [width (count board)]
    (+ (* y width) x)))


(defn get-at [{:keys [:x :y]} board])

(ind-to-xy 14 board)


(defn get-top-left [index board]
  )