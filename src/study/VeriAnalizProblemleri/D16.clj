(ns study.VeriAnalizProblemleri.D16
  (:require [clojure.string :as str]))

;## d16
;
;Girdi:
;
;```clojure
;{1 {:id 1
;  :city "Istanbul"
;  :name {:first "ali" :last "veli"}}
; 2 {:id 2
;  :city "Ankara"
;  :name {:first "batu" :last "can"}}}
;```
;
;Arama anahtar kelimesi:
;
;```
;"ankara"
;```
;
;Bu anahtar kelimeyi `:city` property'sinde arayın. Eşleşen (match eden) property'lerin objelerini dönün.
;
;Çıktı:
;
;```clojure
;[{:id 2
;:city "Ankara"
;:name {:first "batu" :last "can"}}]
;```



(def my-map {1 {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}}
             2 {:id 2 :city "Ankara" :name {:first "batu" :last "can"}}})




(defn d16 [coll ?s]
  (reduce
    (fn [x y]
      (let [{:keys [id city _]} y]
        (if (str/includes? (str/lower-case city) (str/lower-case ?s))
          (conj x (my-map id) )
          x)))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (get-in my-map [len])
      )))

(d16 my-map "ankara")
;=> [{:id 2, :city "Ankara", :name {:first "batu", :last "can"}}]
