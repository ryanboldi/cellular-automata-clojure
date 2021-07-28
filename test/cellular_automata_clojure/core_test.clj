(ns cellular-automata-clojure.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [cellular-automata-clojure.core :as core]
            [cellular-automata-clojure.rules :as rules]))

(deftest splitting-works
  (testing "basic split"
    (let [input [1 0 1]
          output (core/split-up input)]
      (is (= output '([1 1 0] [1 0 1] [0 1 1])))))
  (testing "lengths are the same"
    (let [input [1 0 1 0 1 0]
          output (core/split-up input)]
      (is (= (count input) (count output))))))


(deftest one-d-rule-prop
  (testing "length of output is the same as the length of the input"
    (let [input (apply vector (repeat 10 0))
          output (core/get-next-state input rules/rule30)]
      (is (= (count input) (count output))))))