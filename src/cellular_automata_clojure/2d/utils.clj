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

(defn xy-to-ind
  "converts xy coords into a flattened index"
  [{:keys [:x :y]} board]
  (let [width (count board)]
    (+ (* y width) x)))

(defn nth-with-wrapping [coll index]
  (let [len (count coll)]
    (cond
      (> index (dec len)) (nth-with-wrapping coll (- index len))
      (< index 0) (nth-with-wrapping coll (+ index len))
      :else (nth coll index))))

(defn get-at-xy
  "returns the value at a given xy coord - includes wrapping"
  [{:keys [:x :y]} board]
  (nth-with-wrapping (nth-with-wrapping board y) x))

(defn get-neighbors-from-xy
  [{:keys [:x :y]} board]
  (let [top-left (get-at-xy {:x (dec x) :y (dec y)} board)
        top-mid (get-at-xy {:x x :y (dec y)} board)
        top-right (get-at-xy {:x (inc x) :y (dec y)} board)
        left (get-at-xy {:x (dec x) :y y} board)
        mid (get-at-xy {:x x :y y} board)
        right (get-at-xy {:x (inc x) :y y} board)
        bot-left (get-at-xy {:x (dec x) :y (inc y)} board)
        bot-mid (get-at-xy {:x x :y (inc y)} board)
        bot-right (get-at-xy {:x (inc x) :y (inc y)} board)]
    (vector top-left top-mid top-right left mid right bot-left bot-mid bot-right)))

(defn get-neighbors
  "returns a vector of the 9 neighbours of a given flattened index"
  [index board]
  (get-neighbors-from-xy (ind-to-xy index board) board))

(defn get-all-neighbors [board]
  (let [flat-board (flatten board)
       len (count flat-board)]
    (map #(get-neighbors % board) (range 0 (dec len)))))

(get-all-neighbors (vector (vector 0 0 0) (vector 0 1 1)  (vector 1 1 0)))

;(map conways-game-of-life (map #(get-neighbors % board)))

;(defn apply-rule [rule board])