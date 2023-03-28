(ns study.VeriAnalizProblemleri.D06
  (:require [study.reusable-functions :as rf]))


;```clojure
;[1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}]
;```
;
;Çıktı:
;
;```clojure
;[1 ["ali" "veli"]
; 2 ["batu" "can"]]



(def my-vec [1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             ])

(def my-map (rf/transform-outer-coll-to-map my-vec))

(rf/transform-outer-coll-to-vector (map (juxt :name :surname) (for [lenght (range 1 (+ 1 (count my-map)))]
                                                                (my-map lenght)
                                                                )))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]



(mapv                                                       ;mapv

  (fn [o] (if (map? o)                                      ;map function --> map applies 1 -->it is not a map so doing nothing
            ((juxt :name :surname) o) o)                    ;Then map applies next into {:id 1 ...} --> it is a map yes so function getting :name :surname
    )                                                       ;map applies 2--> it is not a map so doing nothing
  ;Then map applies next into {:id 2 ...} --> it is a map yes so function getting :name :surname
  ;and after map function is done returning a new vector=> ;[1 ["ali" "veli"] 2 ["batu" "can"]


  my-vec)                                                   ;collection
;=> [1 ["ali" "veli"] 2 ["batu" "can"]]


(mapv ;; we use mapv instead of map to get the result in the desired vector form
  (fn [item]
    (if (map? item)
      [(:name item)(:surname item)]
      ;; else unchanged:
      item))
  my-vec)
;=> [1 ["ali" "veli"] 2 ["batu" "can"]]



(let [my-vec [1 {:id 1 :name "ali" :surname "veli"}
              2 {:id 2 :name "batu" :surname "can"}]
      [a {n1 :name s1 :surname}
       b {n2 :name s2 :surname}] my-vec]
  [a [n1 s1] b [n2 s2]])
;=> [1 ["ali" "veli"] 2 ["batu" "can"]]



(def my-new-vec [
                 1 {:id 1 :name "ali" :surname "veli"} {:id 1 :name "ali" :surname "veli"} 2 {:id 1 :name "ali" :surname "veli"}
                 2 {:id 1 :name "ali" :surname "veli"} 2 {:id 1 :name "ali" :surname "veli"}
                 3 2 {:id 1 :name "ali" :surname "veli"}
                 ])

(mapv (fn [o]
        (if (map? o)
          ((juxt :name :surname) o)
          o))
      my-new-vec)
;=> [1 ["ali" "veli"] ["ali" "veli"] 2 ["ali" "veli"] 2 ["ali" "veli"] 2 ["ali" "veli"] 3 2 ["ali" "veli"]]
