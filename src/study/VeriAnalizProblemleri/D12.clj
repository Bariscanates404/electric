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


(let [{:keys [first last]}  (get-in (get-in my-vec [0]) [:name])]
  ( pr first last))
;1 ali veli

(get-in (get-in my-vec [0]) [:name])


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
