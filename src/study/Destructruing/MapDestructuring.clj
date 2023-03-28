(ns study.Destructruing.MapDestructuring)

(def car-map {:make "bwm"
              :model ["m4" "m3" "2 series"]
              :country "Germany"}
  )

(defn destructure-fn [x]
  (let [{make :make} x]
    make))

(destructure-fn car-map)
;=> "bwm"

(defn destructure-fn [x]
  (let [{country :country} x]
    country))

(destructure-fn car-map)
;=> "Germany"


(defn destructure-fn [x]
  (let [{country :country
         make :make} x]
    (str make " " country)))

(destructure-fn car-map)
;=> "bwm Germany"


(defn destructure-fn [x]
  (let [{:keys [make country model]} x]
    (str make " " country " " model)))

(destructure-fn car-map)
;=> "bwm Germany [\"m4\" \"m3\" \"2 series\"]"


;-----------------------------------XXX----------------------------------
(defn destructure-fn [x]
  (let [{:keys [make country] [first-model _ second-model] :model ]} x]
    (str make " " country " " model)))

(destructure-fn car-map)

;-----------------------------------XXX----------------------------------