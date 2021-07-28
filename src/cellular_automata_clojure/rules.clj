(ns cellular-automata-clojure.rules)

(def rule30
  {'(0 0 0) 0
   '(0 0 1) 1
   '(0 1 0) 1
   '(1 0 0) 1
   '(0 1 1) 1
   '(1 1 0) 0
   '(1 0 1) 0
   '(1 1 1) 0})