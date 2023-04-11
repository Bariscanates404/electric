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


(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             })

(def my-vec (into [] (for [m (vals my-map) [k v] m] [(name k) v])))
;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]


(defn filter-vector-func [coll ?s]
  (reduce
    (fn [x y]
      (let [[ :as all] y]
        (if (str/includes? (str/lower-case all) (str/lower-case ?s))
          (conj x all)
          x)))
    []
    coll))

(rf/transform-to-vector(filter-vector-func my-vec "a"))
;[["name" "ali"] ["name" "batu"] ["surname" "veli"] ["surname" "can"]]

(rf/transform-to-vector(filter-vector-func my-vec "v"))
;=> [["surname" "veli"]]



(let [[first second :as all] my-vec]
  all)


(defn filter-vector-func [coll search-str]
  (->> coll
       (filter (partial some (fn [str-vec]
                               (-> str-vec
                                   clojure.string/lower-case
                                   (clojure.string/includes? search-str)))))))

(filter-vector-func my-vec "a")


(defn filter-vector-func-by-postwalk [coll search-str]
  (->> coll
       (clojure.walk/postwalk
         (fn [form]
           (let [form (if (vector? form)
                        (vec (keep identity form))
                        form)]
             (cond
               (and (vector? form)
                    (every? vector? form)) form
               (and (vector? form)
                    (not (every? string? form))) nil
               (and (vector? form)
                    (every? string? form)
                    (->> form
                         (some (fn [str-vec]
                                 (-> str-vec
                                     clojure.string/lower-case
                                     (clojure.string/includes? search-str)))))) form
               :else form))))
       (keep identity)
       vec))

(filter-vector-func-by-postwalk my-vec "a")