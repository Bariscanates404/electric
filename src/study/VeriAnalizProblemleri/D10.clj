(ns study.VeriAnalizProblemleri.D10
  (:require
    [clojure.string :as str]
    [study.reusable-functions :as rf]))

;Girdi:
;
;```clojure
;{1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}}
;```
;
;Arama anahtar kelimesi:
;
;```
;"a"
;```
;
;Bu anahtar kelimeyi tüm property'lerde arayın. Eşleşen property'leri key-value ikilileri olarak dönün.
;
;Çıktı:
;
;```clojure
;[["name" "ali"] ["name" "batu"] ["surname" "can"]]
;```


(def my-map {:id 1 :name "ali" :surname "veli"}
  )
