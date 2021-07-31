# cellular-automata-clojure

A Clojure library designed to handle various forms of cellular automata through the console.


This library supports multiple forms of cellular automata:

&nbsp;

## 1 Dimensional
To run a simple one dimensional cellular automata, pick a rule from [this file](src\cellular_automata_clojure\1d\rules.clj), or add your own. Some example rules are [rule30](https://en.wikipedia.org/wiki/Rule_30) and [rule110](https://en.wikipedia.org/wiki/Rule_110):

```clojure
(def rule30
  {'(0 0 0) 0
   '(0 0 1) 1
   '(0 1 0) 1
   '(1 0 0) 1
   '(0 1 1) 1
   '(1 1 0) 0
   '(1 0 1) 0
   '(1 1 1) 0})

(def rule110
  {'(0 0 0) 0
   '(0 0 1) 1
   '(0 1 0) 1
   '(1 0 0) 0
   '(0 1 1) 1
   '(1 1 0) 1
   '(1 0 1) 1
   '(1 1 1) 0})
```

After this, pick a set of starting conditions from [this file](src\cellular_automata_clojure\1d\boards.clj), or add your own.
Lonesome one is a starting condition where there is a single one in the middle of a sea of zeroes on either side. The two default versions of this have either 15 zeroes on either side, or 50. You can add your own to change this number and subsequently change the length of the entire cellular automata.

```clojure
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
```

Now that you've picked the ruleset and starting conditions, to run a simple 1D cellular automata, run the following command:

```
lein run -m cellular-automata-clojure.1d.examples :generations 50 :rule rule30 :start lonesome-one-short
```

### Examples:
<details>
    <summary>
        <code>
lein run -m cellular-automata-clojure.1d.examples :generations 10 :rule rule30 :start lonesome-one-short
        </code>
    </summary>
    =>
<pre><code>
..............#..............
.............###.............
............##..#............
...........##.####...........
..........##..#...#..........
.........##.####.###.........
........##..#....#..#........
.......##.####..######.......
......##..#...###.....#......
.....##.####.##..#...###.....
</code></pre>
</details>

<details>
    <summary>
        <code>
lein run -m cellular-automata-clojure.1d.examples :generations 50 :rule rule30 :start lonesome-one-long
        </code>
    </summary>
=>
<pre><code>
.................................................#.................................................
................................................###................................................
...............................................##..#...............................................
..............................................##.####..............................................
.............................................##..#...#.............................................
............................................##.####.###............................................
...........................................##..#....#..#...........................................
..........................................##.####..######..........................................
.........................................##..#...###.....#.........................................
........................................##.####.##..#...###........................................
.......................................##..#....#.####.##..#.......................................
......................................##.####..##.#....#.####......................................
.....................................##..#...###..##..##.#...#.....................................
....................................##.####.##..###.###..##.###....................................
...................................##..#....#.###...#..###..#..#...................................
..................................##.####..##.#..#.#####..#######..................................
.................................##..#...###..####.#....###......#.................................
................................##.####.##..###....##..##..#....###................................
...............................##..#....#.###..#..##.###.####..##..#...............................
..............................##.####..##.#..######..#...#...###.####..............................
.............................##..#...###..####.....####.###.##...#...#.............................
............................##.####.##..###...#...##....#...#.#.###.###............................
...........................##..#....#.###..#.###.##.#..###.##.#.#...#..#...........................
..........................##.####..##.#..###.#...#..####...#..#.##.######..........................
.........................##..#...###..####...##.#####...#.#####.#..#.....#.........................
........................##.####.##..###...#.##..#....#.##.#.....#####...###........................
.......................##..#....#.###..#.##.#.####..##.#..##...##....#.##..#.......................
......................##.####..##.#..###.#..#.#...###..####.#.##.#..##.#.####......................
.....................##..#...###..####...####.##.##..###....#.#..####..#.#...#.....................
....................##.####.##..###...#.##....#..#.###..#..##.####...###.##.###....................
...................##..#....#.###..#.##.#.#..#####.#..######..#...#.##...#..#..#...................
..................##.####..##.#..###.#..#.####.....####.....####.##.#.#.#########..................
.................##..#...###..####...####.#...#...##...#...##....#..#.#.#........#.................
................##.####.##..###...#.##....##.###.##.#.###.##.#..#####.#.##......###................
...............##..#....#.###..#.##.#.#..##..#...#..#.#...#..####.....#.#.#....##..#...............
..............##.####..##.#..###.#..#.####.####.#####.##.#####...#...##.#.##..##.####..............
.............##..#...###..####...####.#....#....#.....#..#....#.###.##..#.#.###..#...#.............
............##.####.##..###...#.##....##..###..###...######..##.#...#.###.#.#..####.###............
...........##..#....#.###..#.##.#.#..##.###..###..#.##.....###..##.##.#...#.####....#..#...........
..........##.####..##.#..###.#..#.####..#..###..###.#.#...##..###..#..##.##.#...#..######..........
.........##..#...###..####...####.#...######..###...#.##.##.###..######..#..##.#####.....#.........
........##.####.##..###...#.##....##.##.....###..#.##.#..#..#..###.....######..#....#...###........
.......##..#....#.###..#.##.#.#..##..#.#...##..###.#..##########..#...##.....####..###.##..#.......
......##.####..##.#..###.#..#.####.###.##.##.###...####.........####.##.#...##...###...#.####......
.....##..#...###..####...####.#....#...#..#..#..#.##...#.......##....#..##.##.#.##..#.##.#...#.....
....##.####.##..###...#.##....##..###.###########.#.#.###.....##.#..#####..#..#.#.###.#..##.###....
...##..#....#.###..#.##.#.#..##.###...#...........#.#.#..#...##..####....######.#.#...####..#..#...
..##.####..##.#..###.#..#.####..#..#.###.........##.#.#####.##.###...#..##......#.##.##...#######..
.##..#...###..####...####.#...######.#..#.......##..#.#.....#..#..#.#####.#....##.#..#.#.##......#.
##.####.##..###...#.##....##.##......#####.....##.###.##...########.#.....##..##..####.#.#.#....###
</code></pre>
</details>

&nbsp;
&nbsp;

## 2 Dimensional

As with the 1D cellular automata, 2D CAs are also controlled by a ruleset and a starting board. Due to the vastly larger amount of possible states in a 3x3 area (compared to the 3x1 area in a 1D CA), it is a lot harder to program every possible state and how it maps to the next generation. Instead, with 2D CAs, the rulesets are more programmatic, where the user can check for certain patterns and decide what to return. Below is an example of how 'Conway's game of life' 2D CA was implemented in this library:

```clojure
(defn conways-game-of-life
  "Any live cell with fewer than two live neighbours dies, as if by underpopulation.
   Any live cell with two or three live neighbours lives on to the next generation.
   Any live cell with more than three live neighbours dies, as if by overpopulation. 
   Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."
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
```

The input to the rule functions is a length 9 vector that contains the "neigborhood" of the current pixel. The ruleset should return either a 1 or a 0, depending on whether this position should be active or not in the next generation.

Once you pick a set of rules (mappings from a length 9 vector to a single number), you must decide on the game board (and therefore the game shape). Here are a few game boards that have been included that result in some interesting behavior:

```clojure
;\src\cellular_automata_clojure\2d\boards.clj

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

(defn big-board [n]
  (apply vector
         (concat (repeat (/ n 2) (apply vector (repeat n 0)))
                 (repeat (/ n 2) (apply vector (repeat n 1))))))

(def big-board-small (big-board 30))
(def big-board-big (big-board 100))
```

Once one of these square boards are picked, you are ready to go. Run the following command to have a command-line view of the cellular automata doing its thing:

```
lein run -m cellular-automata-clojure.2d.examples :generations 100 :rule conways-game-of-life :start big-board-small
```

### Example
<details>
    <summary>
        <code>
lein run -m cellular-automata-clojure.2d.examples :generations 5 :rule conways-game-of-life :start blinker-board
        </code>
    </summary>
=>
<pre><code>
.....
..#..
..#..
..#..
.....

.....
.....
.###.
.....
.....

.....
..#..
..#..
..#..
.....

.....
.....
.###.
.....
.....

.....
..#..
..#..
..#..
.....
</code></pre>
</details>

## Testing
Run the tests (with automatic refresh):

```powershell
lein test-refresh
```

## License

Copyright © 2021 Ryan Boldi

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
