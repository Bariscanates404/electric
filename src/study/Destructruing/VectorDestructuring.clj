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

