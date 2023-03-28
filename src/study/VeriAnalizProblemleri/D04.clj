(ns study.VeriAnalizProblemleri.D04
  (:require [study.reusable-functions :as rf]))

;Girdi:
;
;```clojure
;[{:id 1 :name "ali" :surname "veli"}
; {:id 2 :name "batu" :surname "can"}]
;```
;
;Çıktı:
;Task 4 <---
;```clojure
;[[1 "ali" "veli"]
; [2 "batu" "can"]]
;```

(def my-vec [{:id 1 :name "ali" :surname "veli"}
             {:id 2 :name "batu" :surname "can"}])


(rf/transform-outer-coll-to-vector (map (juxt :id :name :surname) my-vec))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]

(->> my-vec (mapv (comp vec vals)))
;=> [[1 "ali" "veli"] [2 "batu" "can"]]

(mapv (fn [data] [(:id data) (:name data) (:surname data)]) my-vec)
;=> [[1 "ali" "veli"] [2 "batu" "can"]]
