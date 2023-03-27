(ns study.VeriAnalizProblemleri.D01
  (:require [study.reusable-functions :as rf]))


;Girdi:
;
;```clojure
;[{:name "ali" :surname "veli"}
; {:name "batu" :surname "can"}]
;```
;
;Çıktı:
;
;```clojure
;[["ali" "veli"]
; ["batu" "can"]]

;(map (fn [[k v]] [k v]) {:a 1 :b 2}) ; ([:a 1] [:b 2])

(def my-vec [{:name "ali" :surname "veli"}
             {:name "batu" :surname "can"}])

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



(println
  (rf/transform-outer-coll-to-vector (rf/flatten-one-level (for [lenght (range 0 (count my-vec))]
                                                             (update [] 0 #(str (into [] (vals (get my-vec lenght))) %)))))
  )

;==> [["ali" "veli"] ["batu" "can"]]
