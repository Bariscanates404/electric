(ns study.VeriAnalizProblemleri.D02
  (:require [study.reusable-functions :as rf]))

(def my-vec [{:name "ali" :surname "veli"}
             {:name "batu" :surname "can"}])

;Girdi:
;
;```clojure
;[{:name "ali" :surname "veli"}
; {:name "batu" :surname "can"}]
;```
;
;Çıktı:
;Task 2 <---
;```clojure
;["aliveli" "batucan"]
;```


(into []
      (rf/flatten-one-level
        (mapv (fn [data] [(apply str (:name data) (:surname data))])
              my-vec)))
;=> ["aliveli" "batucan"]




