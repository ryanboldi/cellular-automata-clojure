(ns cellular-automata-clojure.2d-test
  (:require [clojure.test :refer [deftest testing is]]
            [cellular-automata-clojure.2d.utils :as utils]))

(deftest index-to-xy-test
  (let [board (apply vector (repeat 3 (apply vector (repeat 3 0))))]
    (testing "3x3 board simple"
      (is (= (utils/map-to-xy 0 board) {:x 0 :y 0})))
    (testing "3x3 board last digit"
      (is (= (utils/map-to-xy 8 board) {:x 2 :y 2}))))
  (let [board (apply vector (repeat 100 (apply vector (repeat 100 0))))]
    (testing "100x100 board simple"
      (is (= (utils/map-to-xy 99 board) {:x 99 :y 0})))
    (testing "100x100 board last digit"
      (is (= (utils/map-to-xy (dec (* 100 100)) board) {:x 99 :y 99})))))