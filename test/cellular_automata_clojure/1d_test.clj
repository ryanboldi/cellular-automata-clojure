(ns cellular-automata-clojure.1d-test
  (:require [clojure.test :refer [deftest testing is]]
            [cellular-automata-clojure.1d.utils :as utils]
            [cellular-automata-clojure.1d.rules :as rules]) )

(deftest splitting-works
  (testing "basic split"
    (let [input [1 0 1]
          output (utils/split-up input)]
      (is (= output '([1 1 0] [1 0 1] [0 1 1])))))
  (testing "lengths are the same"
    (let [input [1 0 1 0 1 0]
          output (utils/split-up input)]
      (is (= (count input) (count output))))))

(deftest one-d-rule-prop
  (testing "length of output is the same as the length of the input"
    (let [input (apply vector (repeat 10 0))
          output (utils/get-next-state input rules/rule30)]
      (is (= (count input) (count output))))))