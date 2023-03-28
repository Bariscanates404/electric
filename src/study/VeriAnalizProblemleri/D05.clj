(ns study.VeriAnalizProblemleri.D05
  (:require [study.reusable-functions :as rf]))

;Girdi:
;
;```clojure
;[1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}]
;```
;
;Çıktı:
;Task 5 <---
;```clojure
;[[1 "ali" "veli"]
; [2 "batu" "can"]]


(def my-vec
  [1 {:id 1 :name "ali" :surname "veli"}
   2 {:id 2 :name "batu" :surname "can"}])


(def my-map (rf/transform-outer-coll-to-map my-vec))
(identity my-map)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}




(mapv
  (fn [data] [(:id data) (:name data) (:surname data)]) (for [lenght (range 1 (+ 1 (count my-map)))]
                                                          (my-map lenght)
                                                          ))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]


(rf/transform-outer-coll-to-vector(map (juxt :id :name :surname) (for [lenght (range 1 (+ 1 (count my-map)))]
                                                               (my-map lenght)
                                                               )))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]



(mapv (juxt :id :name :surname) (for [lenght (range 1 (+ 1 (count my-map)))]
                              (my-map lenght)
                              ))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]


(->> (for [lenght (range 1 (+ 1 (count my-map)))]
       (my-map lenght)
       ) (mapv (comp vec vals)))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]

















;;;;
(comment
  (defn indices-of [f coll]
    (keep-indexed #(if (f %2) %1 nil) coll))

  (defn first-index-of [f coll]
    (first (indices-of f coll)))

  (defn find-thing [value coll]
    (first-index-of #(= % value) coll))

  (find-thing "two" ["one" "two" "three" "two"])            ; 1
  (find-thing "two" '("one" "two" "three"))                 ; 1

  ;; these answers are a bit silly
  (find-thing "two" #{"one" "two" "three"})                 ; 1
  (find-thing "two" {"one" "two" "two" "three"})            ; nil
   ,)
