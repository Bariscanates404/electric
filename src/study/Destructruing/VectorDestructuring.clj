(ns study.Destructruing.VectorDestructuring)


(def list-of-cars ["bmw" "mercedes" "fiat"])
(defn destructure-fn [[first-make _ second-make]]
  first-make )

(destructure-fn list-of-cars)
;=> "bmw"

(defn destructure-fn [[first-make _ second-make]]
   second-make)

(destructure-fn list-of-cars)
;=> "fiat"

(defn destructure-fn [[first-make _ second-make]]
  _)
(destructure-fn list-of-cars)
;=> "mercedes"




;;-------------------------------------LET BINDING---------------------------------------

(defn destructure-fn [x]
  (let [[first-make _ second-make] x]
    first-make ))

(destructure-fn list-of-cars)
;=> "bmw"

(def list-of-cars-models [["bmw" "m4"] ["mercedes" "a200"] ["fiat" "500"]])

(defn destructure-fn [x]
  (let [[first-make _ second-make] x]
    second-make ))

(destructure-fn list-of-cars-models)
;=> "fiat" "500"



(defn destructure-fn [x]
  (let [[[label model] _ second-make] x]
    (str label " " model) ))

(destructure-fn list-of-cars-models)
;=> "bmw m4"

;-----------------------------------XXX----------------------------------
(let [source [1 2 3 4 5 6]
      x (first source)
      y (second source)
      z (nth source 2)
      the-rest (rest source)]
  z)
;-----------------------------------XXX----------------------------------

(let [[x y z _ _ t] [1 2 3 4 5 6]]
  t)
;=> 6

(let [[x y z & the-rest] [1 2 3 4 5 6]]
  the-rest)
;=> (4 5 6)

(let [[x y z & the-rest :as all] [1 2 3 4 5 6]]
  all)
;=> [1 2 3 4 5 6]

(let [[x y z & the-rest :as all] {1 2
                                  3 4
                                  5 6
                                  8 9}]
  all)

;-----------------------------------XXX----------------------------------

(def my-list '("Bronn" "Hound" "Mountain"))
(let [[name-1 name-2 name-3]my-list]
  (str name-1))
;=> "Bronn"


(def my-list '("Bronn" "Hound" "Mountain"))
(let [[name-1 name-2 name-3]my-list]
  (str name-2))
;=> "Hound"

(def my-list '("Bronn" "Hound" "Mountain"))
(let [[name-1 name-2 name-3]my-list]
  (str name-3 " " name-2 " " name-1))
;=> "Mountain Hound Bronn"

(def my-string "Jon")
(let [[char-1 char-2 char-3] my-string]
  (str char-1 " " char-3 " " char-2))
;=> "J n o"




