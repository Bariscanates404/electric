(ns study.MantikAnaliziCalismalari.E15
  (:require [study.reusable-functions :as rf])
  (:use demo.core
        tupelo.core
        tupelo.test)
  )

(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}})

((juxt :id :name :surname) {:id 1 :name "ali" :surname "veli"})
;=> [1 "ali" "veli"]

(juxt :id :name :surname)
;=> [[1 "ali" "veli"] [2 "batu" "can"]]

((juxt take drop) 3 [1 2 3 4 5 6])
;juxt formuna arguman olarak verilen fonksiyonları soldan sağa doğru çalıştırır.


(def my-flat-map {:id 2 :name "batu" :surname "can"})
((juxt :name :surname) my-flat-map)

((juxt + * min max) 3 4 6)

;((juxt a b c) x) => [ (a x) (b x) (c x)]

((juxt (partial filter even?) (partial filter odd?)) (range 0 9))
;;=> [(0 2 4 6 8) (1 3 5 7)]

((juxt :lname :fname) {:fname "Bill" :lname "Gates"})