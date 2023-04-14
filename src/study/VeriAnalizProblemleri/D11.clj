(ns study.VeriAnalizProblemleri.D11
  (:require [clojure.string :as str]))

;Arama anahtar kelimesi:
;
;```
;"a"
;```
;
;Bu anahtar kelimeyi tüm property'lerde arayın. Eşleşen property'leri aşağıdaki formda dönün:
;
;Çıktı:
;
;```clojure
;[[1 "ali"] [2 "batu"] [2 "can"]]


(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             }
  )


;-------------------------------------------d01------------------------------------------------
;==> a01
(defn filter-map-func-name [coll ?s]
  (reduce
    (fn [x y]
      (let [{:keys [id name surname]} y]
        (if (str/includes? (str/lower-case name) (str/lower-case ?s))
          (conj x (vector id name))
          x)))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (my-map len)
      )))

(filter-map-func-name my-map "a")
;=> [[1 "ali"] [2 "batu"]]


(defn filter-map-func-surname [coll ?s]
  (reduce
    (fn [x y]
      (let [{:keys [id name surname]} y]
        (if (str/includes? (str/lower-case surname) (str/lower-case ?s))
          (conj x (vector id surname))
          x)))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (my-map len)
      )))

(filter-map-func-surname my-map "a")

(defn search-func-d11 [coll ?s]
  (conj (filter-map-func-name coll ?s) (filter-map-func-surname coll ?s))
  )

(search-func-d11 my-map "a")
;=> [[1 "ali"] [2 "batu"] [[2 "can"]]]

(search-func-d11 my-map "l")
;=> [[1 "ali"] [[1 "veli"]]]



(let [{:keys [id name surname]} {:id 1 :name "ali" :surname "veli"}]
  (println id name surname))
;1 ali veli
