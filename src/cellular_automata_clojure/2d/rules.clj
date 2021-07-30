(ns cellular-automata-clojure.2d.rules)

(defn conways-game-of-life
  "Any live cell with fewer than two live neighbours dies, as if by underpopulation.
   Any live cell with two or three live neighbours lives on to the next generation.
   Any live cell with more than three live neighbours dies, as if by overpopulation. 
   Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   
   Any live cell with two or three live neighbours survives.
   Any dead cell with three live neighbours becomes a live cell.
   All other live cells die in the next generation. Similarly, all other dead cells stay dead."
  [neighbors]
  (let [cell (nth neighbors 4)
        alive-neighbors (- (apply + neighbors) cell)
        cell-alive? (= 1 cell)]
    (if cell-alive?
      (cond
        (< alive-neighbors 2) 0
        (> alive-neighbors 3) 0
        :else 1)
      (cond
        (= alive-neighbors 3) 1
        :else 0))))