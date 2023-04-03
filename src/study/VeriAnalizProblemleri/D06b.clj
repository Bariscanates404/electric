(ns study.VeriAnalizProblemleri.D06b
  (:require [study.reusable-functions :as rf]))






(def my-map {1 {:id 1 :x [:name "ali"] :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}})

(my-map 1)


(rf/transform-outer-coll-to-vector (map (juxt :name :surname) (for [lenght (range 1 (+ 1 (count my-map)))]
                                                                (my-map lenght)
                                                                )))
;=> [["ali" "veli"] ["batu" "can"]]



(rf/transform-maps-to-vector my-map)
;=> [1 [:id 1 :name "ali" :surname "veli"] 2 [:id 2 :name "batu" :surname "can"]]