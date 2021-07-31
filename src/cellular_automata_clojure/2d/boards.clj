(ns cellular-automata-clojure.2d.boards)

(def blinker-board
  (vector
   (vector 0 0 0 0 0)
   (vector 0 0 1 0 0)
   (vector 0 0 1 0 0)
   (vector 0 0 1 0 0)
   (vector 0 0 0 0 0)))

(def beacon-board
  (vector
   (vector 0 0 0 0 0 0)
   (vector 0 1 1 0 0 0)
   (vector 0 1 0 0 0 0)
   (vector 0 0 0 0 1 0)
   (vector 0 0 0 1 1 0)
   (vector 0 0 0 0 0 0)))

; THIS IS AN EXTREMELY COOL STARTING POSITION, keep it in the board file
(defn big-board [n]
  (apply vector
         (concat (repeat (/ n 2) (apply vector (repeat n 0)))
                 (repeat (/ n 2) (apply vector (repeat n 1))))))

(def big-board-small (big-board 30))
(def big-board-big (big-board 100))