(ns cellular-automata-clojure.2d-test
  (:require [clojure.test :refer [deftest testing is]]
            [cellular-automata-clojure.2d.utils :as utils]))

(deftest index-to-xy-test
  (let [board (apply vector (repeat 3 (apply vector (repeat 3 0))))]
    (testing "3x3 board simple"
      (is (= (utils/ind-to-xy 0 board) {:x 0 :y 0})))
    (testing "3x3 board last digit"
      (is (= (utils/ind-to-xy 8 board) {:x 2 :y 2}))))
  (let [board (apply vector (repeat 100 (apply vector (repeat 100 0))))]
    (testing "100x100 board simple"
      (is (= (utils/ind-to-xy 99 board) {:x 99 :y 0})))
    (testing "100x100 board last digit"
      (is (= (utils/ind-to-xy (dec (* 100 100)) board) {:x 99 :y 99})))))

(deftest xy-to-index-test
  (let [board (apply vector (repeat 3 (apply vector (repeat 3 0))))]
    (testing "3x3 board simple"
      (is (= (utils/xy-to-ind {:x 0 :y 0} board) 0)))
    (testing "3x3 board last digit"
      (is (= (utils/xy-to-ind {:x 2 :y 2} board) 8))))
  (let [board (apply vector (repeat 100 (apply vector (repeat 100 0))))]
    (testing "100x100 board simple"
      (is (= (utils/xy-to-ind {:x 99 :y 0} board) 99)))
    (testing "100x100 board last digit"
      (is (= (utils/xy-to-ind {:x 99 :y 99} board) (dec (* 100 100)))))))

(deftest nth-with-wrapping-test
  (let [vec (vector 0 1 2 3 4 5 6 7 8 9)]
    (testing "no-wrap"
      (is (= (utils/nth-with-wrapping vec 3) (nth vec 3))))
    (testing "front-back wrap"
      (is (= (utils/nth-with-wrapping vec -1) (utils/nth-with-wrapping vec 9))))
    (testing "back-front-wrap"
      (is (= (utils/nth-with-wrapping vec 10) (utils/nth-with-wrapping vec 0))))))

(deftest get-at-xy-test
  (let [board (vector (vector 0 1 2) (vector 3 4 5) (vector 6 7 8))]
    (testing "getting the middle in this board"
      (is (= (utils/get-at-xy {:x 1 :y 1} board) 4)))
    (testing "getting the first in this board"
      (is (= (utils/get-at-xy {:x 0 :y 0} board) 0)))
    (testing "left-right wrapping functionality"
      (is (= (utils/get-at-xy {:x -1 :y 0} board) (utils/get-at-xy {:x 2 :y 0} board))))
    (testing "top-down wrapping functionality"
      (is (= (utils/get-at-xy {:x 0 :y -1} board) (utils/get-at-xy {:x 0 :y 2} board))))))

(deftest get-neighbors-test
  (let [board (vector (vector 1 2 3) (vector 4 5 6) (vector 7 8 9))]
    (testing "top left neighbors"
      (is (= (utils/get-neighbors 0 board) (vector 9 7 8 3 1 2 6 4 5))))
    (testing "middle neighbors"
      (is (= (utils/get-neighbors 4 board) (flatten board))))))