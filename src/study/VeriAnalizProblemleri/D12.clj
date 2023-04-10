(ns study.VeriAnalizProblemleri.D12
  (:require
    [clojure.string :as str]
    [study.reusable-functions :as rf]))

;Girdi:
;
;```clojure
;[{:id 1
;:name {:first "ali" :last "veli"}
; {:id 2
;:name {:first "batu" :last "can"}}}
;```
;
;Çıktı:
;
;```clojure
;[["ali" "veli"]
; ["batu" "can"]]
;```



(def my-vec [{:id   1
              :name {:first "ali" :last "veli"}}
             {:id   2
              :name {:first "batu" :last "can"}}]
  )


(let [{:keys [first last]} (get-in (get-in my-vec [0]) [:name])]
  (conj [] first last))
;1 ali veli

(get-in (get-in my-vec [0]) [:name])
;=> {:first "ali", :last "veli"}

(count my-vec)
;=> 2


(defn d11 [coll]
  (reduce
    (fn [x y]
      (let [{:keys [first last]} y]
        (conj x (vector first last))
        ))
    []
    (for [len (range 0 (count coll))]
      (get-in (get-in coll [len]) [:name])
      )))
(d11 my-vec)
;=> [["ali" "veli"] ["batu" "can"]]
