(ns study.VeriAnalizProblemleri.D08
  (:require
    [study.reusable-functions :as rf]
    [clojure.string :as str]
    ))


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
;Bu anahtar kelimeyi tüm property'lerde arayın. Eşleşen (match eden) property'lerin objelerini dönün.
;
;Çıktı:
;
;```clojure
;{1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}}
;```


;-------------------------------------------d01------------------------------------------------

(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             })


(defn filter-map-by-str [data ?s & fields]
  (into (empty data)
        (filter (fn [[_k v]]
                  (some #(str/includes? (% v) ?s) fields)))
        data))

(filter-map-by-str my-map "ca" :name :surname)
;=> {2 {:id 2, :name "batu", :surname "can"}}

;-------------------------------------------d02------------------------------------------------

(defn filter-db-by-surname [substring db]
  (into {}
        (filter (fn [[_ {:keys [id name surname] :as v}]]
                  (str/includes? (str/lower-case v)
                                 (str/lower-case substring))))
        db))

(filter-db-by-surname "a" my-map)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

(comment
  (def substring "a")
  (def db my-map)

  (filter (fn [[_ {:keys [id name surname] :as v}]]
            (str/includes? (str/lower-case v)
                           (str/lower-case substring)))
          db)
  ;=> ([1 {:id 1, :name "ali", :surname "veli"}] [2 {:id 2, :name "batu", :surname "can"}])


  (into {} '([1 {:id 1, :name "ali", :surname "veli"}] [2 {:id 2, :name "batu", :surname "can"}]))

  (defn destructure-fn [x]
    (let [{surname :surname} x]
      surname)),)

(comment
  (identity db)
  ;{1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

  ((fn [[_ {:keys [id name surname] :as v}]]
     (str/includes? (str/lower-case v)
                    (str/lower-case substring)))
   '(1 {:id 1, :name "ali", :surname "veli"}))
  ,)



;-------------------------------------------d03------------------------------------------------

(defn filter-db-by-surname2 [substring db]
  (reduce-kv (fn [m k {:keys [id name surname] :as v}]
               (if (str/includes? (str/lower-case v)
                                  (str/lower-case substring))
                 (assoc m k v)
                 m))
             {} db))

(filter-db-by-surname2 "a" my-map)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}





;-------------------------------------------How do we use a transducer?------------------------------------------------

; Define the transducer with `comp` but in `->` order:
(def xform (comp (map #(+ 2 %))
                 (filter odd?)))
; adds 2, then omits if result is even.

(into [-1 -2] xform (range 10))
; => [-1 -2 3 5 7 9 11]


(comment
  (xform (range 10))
  (map #(+ 2 %) (range 10))
  ;=> (2 3 4 5 6 7 8 9 10 11)
  (filter odd? (map #(+ 2 %) (range 10)))
  ;=> (3 5 7 9 11)

  (into [-1 -2] (filter odd? (map #(+ 2 %) (range 10))))
  ;=> [-1 -2 3 5 7 9 11]
  (into [-1 -2] '(3 5 7 9 11))
  ;=> [-1 -2 3 5 7 9 11],)