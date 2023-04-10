(ns study.MantikAnaliziCalismalari.E05)

(comment
  (def my-map {1 {:id 1 :name "ali" :surname "veli"}
               2 {:id 2 :name "batu" :surname "can"}})

  (mapv
    (fn [data] [(:id data) (:name data) (:surname data)])
    (for [len (range 1 (+ 1 (count my-map)))]
      (my-map len)
      ))
  ;mapv argüman olarak fonksiyon + collection alıyor.
  ;öncelikle
  (def f (fn [data] [(:id data) (:name data) (:surname data)]))
  ;fonksiyonumuzu tanımlayalım.
  (def coll (for [len (range 1 (+ 1 (count my-map)))]
              (my-map len)))
  ;collectionumuzu tanımlayalım.

  (identity coll)
  ;=> ({:id 1, :x [:name "ali"], :surname "veli"} {:id 2, :name "batu", :surname "can"})

  ;şimdi fonksiyonumuzu debug edelim.
  ;1. iteration
  (f {:id 1, :name "ali", :surname "veli"})
  ;=> [1 "ali" "veli"]
  ;2. iteration
  (f {:id 2 :name "batu" :surname "can"})
  ;=> [2 "batu" "can"]
  ;sonuç olarak   => [[1 "ali" "veli"] [2 "batu" "can"]] çıktısını alırız.
  (mapv f coll)
  ;=> [[1 "ali" "veli"] [2 "batu" "can"]]

  )