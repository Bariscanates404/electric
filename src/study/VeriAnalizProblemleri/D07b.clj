(ns study.VeriAnalizProblemleri.D07b
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
;Arama kriteri:
;
;```
;"ca"
;```
;
;Bu anahtar kelimenin geçtiği `surname` property'sine sahip objeleri bulun.
;
;Çıktı:
;
;```clojure
;{2 {:id 2 :name "batu" :surname "can"}}




(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             })

(for [lenght (range 1 (+ 1 (count my-map)))]
  (my-map lenght)
  )


;-------------------------------------------d01------------------------------------------------
(defn filter-db-by-surname [?s db]
  (->> (for [lenght (range 1 (+ 1 (count my-map)))]
         (db lenght))                                           ;=> {:id 2, :name "batu", :surname "can"}
       (filter
         (fn
           [{:keys [id name surname]} ]
           (let [i id
                 k name
                 v surname]
             (if (str/includes? (str/lower-case (str v)) (str/lower-case (str ?s)))
               (into [] {id (my-map i)})
               ())
             )))))

(filter-db-by-surname "ca" my-map)


;-------------------------------------------d02------------------------------------------------
(let [m {1 {:id 1 :name "ali" :surname "veli"}
         2 {:id 2 :name "batu" :surname "can"}
         }]
  (select-keys m (for [[k v] m :when (s/includes? v "v")] k)))


;=> {1 {:id 1, :name "ali", :surname "veli"}}    çalışıyor!



;-------------------------------------------d03------------------------------------------------
(defn destructure-fn [x ?s]
  (let [{:keys [id name surname]} x]
    (let [i id
          k name
          v surname]
      (if (str/includes? (str/lower-case (str v)) (str/lower-case (str ?s)))
        (rf/transform-outer-coll-to-map(flatten(into [] {id (my-map i)})))
        ())
        )
      ))

(for [lenght (range 1 (+ 1 (count my-map)))]
  (if (map? (my-map lenght))
    (destructure-fn (my-map lenght) "ca")
    )
  )

;verilen valuedan keyi döndürüyor
;(keep #(when (= (val %) x)
;         (key %)) my-map)


;-------------------------------------------d04------------------------------------------------



(defn filter-map-by-str [data s & fields]
  (into (empty data)
        (filter (fn [[_k v]]
                  (some #(str/includes? (% v) s) fields)))
        data))


(filter-map-by-str my-map "ca" :surname)
;=> {2 {:id 2, :name "batu", :surname "can"}}

;-------------------------------------------d05------------------------------------------------

(defn filter-db-by-surname [substring db]
  (into {}
        (filter (fn [[_ {:keys [id name surname] :as v}]]
                  (str/includes? (str/lower-case v)
                                 (str/lower-case substring))))
        db))

(filter-db-by-surname "a" my-map)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

;-------------------------------------------d06------------------------------------------------

(defn filter-db-by-surname2 [substring db]
  (reduce-kv (fn [m k {:keys [id name surname] :as v}]
               (if (str/includes? (str/lower-case v)
                                  (str/lower-case substring))
                 (assoc m k v)
                 m))
             {} db))

(filter-db-by-surname2 "a" my-map)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

