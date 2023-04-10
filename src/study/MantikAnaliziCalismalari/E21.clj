(ns study.MantikAnaliziCalismalari.E21
  (:require [clojure.string :as str]))


(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             })

(def my-vec (into []
                  (for [m (vals my-map) [k v] m]
                    [(name k) v])))



(defn filter-vector-func [coll ?s]
  (reduce
    (fn [x y]
      (let [[ :as all] y]
        (if (str/includes? (str/lower-case all) (str/lower-case ?s))
          (conj x all)
          x)))
    []
    coll))
(filter-vector-func my-vec "s")
