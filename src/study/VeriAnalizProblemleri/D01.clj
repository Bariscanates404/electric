(ns study.VeriAnalizProblemleri.D01
  (:require [study.reusable-functions :as rf]))


;Input:     <--------
;
;```clojure
;[{:name "ali" :surname "veli"}
; {:name "batu" :surname "can"}]
;```
;
;Output:    <-------
;task 1     <---
;```clojure
; [["ali" "veli"]
; ["batu" "can"]]


(def my-vec [{:name "ali" :surname "veli"}
             {:name "batu" :surname "can"}])

(rf/transform-outer-coll-to-vector (rf/flatten-one-level (for [lenght (range 0 (count my-vec))]
                                                           (update [] 0 #(str (into [] (vals (get my-vec lenght))) %)))))
;task 1     <---
;==> [["ali" "veli"] ["batu" "can"]]



(rf/transform-outer-coll-to-vector(map (juxt :name :surname) my-vec))
;=> [["ali" "veli"] ["batu" "can"]]
(mapv (juxt :name :surname) my-vec)
;=> [["ali" "veli"] ["batu" "can"]]

(->> my-vec (mapv (comp vec vals)))
;=> [["ali" "veli"] ["batu" "can"]]

(mapv (fn [data] [(:name data) (:surname data)]) my-vec)
;=> [["ali" "veli"] ["batu" "can"]]














(flatten (flatten my-vec))
;=> ({:name "ali", :surname "veli"} {:name "batu", :surname "can"})

(map (fn [[k v]] [k v]) {:a 1 :b 2})                        ; ([:a 1] [:b 2])

;=> (:name "ali" :surname "veli" :name "batu" :surname "can")


(def atom-vec (atom []))

(vals (get my-vec 1))
(get my-vec 1)
(for [lenght (range 0 2)]
  (map prn (into [] (vals (get my-vec lenght))) [my-vec])
  )

;=> ([["ali" "veli"] ["batu" "can"] ["ali" "veli"]] [["ali" "veli"] ["batu" "can"] ["ali" "veli"] ["batu" "can"]])



