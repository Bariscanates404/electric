(ns study.MantikAnaliziCalismalari.E23
  (:require [clojure.string :as str]))
;date: 20230417
;rfr: src/study/VeriAnalizProblemleri/D10.clj -> a01

(def m {1      {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             })

(def v (into [] (for [m (vals m) [k v] m] [(name k) v])))
;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

;-------------------------------------------d01------------------------------------------------
;a01 ==> burada standart reduce fonksiyonu ile yaptık.


(defn filter-vector-func2 [coll ?s]
  (reduce
    (fn [x y]
      (let [[:as all] y]
        (if (str/includes? (str/lower-case all) (str/lower-case ?s))
          (conj x all)
          x)))
    []
    coll))

(comment

  (def ?s "x")
  (def f (fn [x y]
           (let [[:as all] y]
             (if (str/includes? (str/lower-case all) (str/lower-case ?s))
               (conj x all)
               x))))


  (def y ["name" "ali"])

  ((fn [x y]
     (let [[:as all] y]
       (if (str/includes? (str/lower-case all) (str/lower-case ?s))
         (conj x all)
         x)))
   []
   ["name" "ali"]
   )

  (fn []
    (let [[:as all] ["name" "ali"]]
      (if (str/includes? (str/lower-case all) (str/lower-case ?s))
        (conj [] all)
        [])))

  (if (str/includes? (str/lower-case "ali") (str/lower-case "a"))
    (conj [] "ali")
    [])
  ;=> ["ali"]
  (if (str/includes? (str/lower-case "veli") (str/lower-case "a"))
    (conj [] "ali")
    [])
  ;=> ["veli" "ali"]



  (def ?s "a")
  (let [[:as all] ["name" "ali"]]
    (if (str/includes? (str/lower-case all) (str/lower-case ?s))
      (conj [] all)
      []))
  ;==> [["name" "ali"]]

  (def ?s "x")
  (let [[:as all] ["name" "ali"]]
    (if (str/includes? (str/lower-case all) (str/lower-case ?s))
      (conj [] all)
      []))
  ;==> []

  (if (str/includes? (str/lower-case ["ali" "veli"]) (str/lower-case "a"))
    (conj [] ["ali" "veli"])
    [])
  ;=> [["name" "ali"]]

  (str/lower-case ["ali" "veli"])
  ;=> "[\"ali\" \"veli\"]"

  )

