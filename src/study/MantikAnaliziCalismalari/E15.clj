(ns study.MantikAnaliziCalismalari.E15)

(mapv (juxt :id :name :surname) (for [len (range 1 (+ 1 (count my-map)))]
                                  (my-map len)
                                  ))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]

