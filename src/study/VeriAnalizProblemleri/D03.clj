(ns study.VeriAnalizProblemleri.D03
  (:require [study.reusable-functions :as rf]))



  ;Girdi:
;
;```clojure
;[{:name "ali" :surname "veli"}
; {:name "batu" :surname "can"}]
;```
;
;Çıktı:
;Task 3 <---
;```clojure
;[{"name" "ali" "surname" "veli"}
; {"name" "batu" "surname" "can"}]
;```


(def my-vec [{:name "ali" :surname "veli"}
             {:name "batu" :surname "can"}])

(def my-map (into #{} my-vec))
(identity my-map)

; => (stringify-keys m)
;  (apply nn/add-to-index (list* (:id node) (:name idx) (map name (apply concat data))))


(rf/keywordize-keys [{"name" "ali", "surname" "veli"} {"name" "batu", "surname" "can"}])
;=> [{:name "ali", :surname "veli"} {:name "batu", :surname "can"}]

(clojure.walk/keywordize-keys [{"name" "ali", "surname" "veli"} {"name" "batu", "surname" "can"}])
;=> [{:name "ali", :surname "veli"} {:name "batu", :surname "can"}]



(rf/stringify-keys-2 my-vec)
;=> [{"name" "ali", "surname" "veli"} {"name" "batu", "surname" "can"}]

(clojure.walk/stringify-keys my-vec)
;=> [{"name" "ali", "surname" "veli"} {"name" "batu", "surname" "can"}]


