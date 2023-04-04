(ns study.VeriAnalizProblemleri.D05b
  (:require [study.reusable-functions :as rf]))


;Girdi:
;
;```clojure
;{1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}}
;```
;
;Çıktı:
;
;```clojure
;[[1 "ali" "veli"]
; [2 "batu" "can"]]

(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}})




(mapv
  (fn [data] [(:id data) (:name data) (:surname data)]) (for [len (range 1 (+ 1 (count my-map)))]
                                                          (my-map len)
                                                          ))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]


(rf/transform-outer-coll-to-vector(map (juxt :id :name :surname) (for [len (range 1 (+ 1 (count my-map)))]
                                                                   (my-map len)
                                                                   )))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]



(mapv (juxt :id :name :surname) (for [len (range 1 (+ 1 (count my-map)))]
                                  (my-map len)
                                  ))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]


(->> (for [len (range 1 (+ 1 (count my-map)))]
       (my-map len)
       ) (mapv (comp vec vals)))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]





