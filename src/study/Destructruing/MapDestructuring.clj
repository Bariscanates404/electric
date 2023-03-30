(ns study.Destructruing.MapDestructuring)

(def car-map {:make    "bwm"
              :model   ["m4" "m3" "2 series"]
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
         make    :make} x]
    (str make " " country)))

(destructure-fn car-map)
;=> "bwm Germany"


(defn destructure-fn [x]
  (let [{:keys [make country model]} x]
    (str make " " country " " model)))

(destructure-fn car-map)
;=> "bwm Germany [\"m4\" \"m3\" \"2 series\"]"


;-----------------------------------XXX----------------------------------
(let [{a :a} {:a  "A"
              :b  "B"
              :c  "C"
              "d" "D"}]
  a)
;=> "A"

(let [{:keys [a b c]} {:a  "A"
                       :b  "B"
                       :c  "C"
                       "d" "D"}]
  c)
;=> "C"

(let [{:keys [a b c d]} {:a  "A"
                         :b  "B"
                         :c  "C"
                         "d" "D"}]
  d)
;=> nil  d keyword degil bu sebeple nill doner.

(let [{:keys [a b c d]
       :strs [d]} {:a  "A"
                   :b  "B"
                   :c  "C"
                   "d" "D"}]
  d)
;=> "D"     d keyword degil bir string keybu sebeple :strs i kullanarak döndurebiliriz.

(let [{:keys [a b c d]
       :strs [d]
       :syms [e]} {:a  "A"
                   :b  "B"
                   :c  "C"
                   "d" "D"
                   'e  "E"}]
  e)
;=> "E"

(let [{:keys [a b c d]
       :strs [d]
       :syms [e]
       :as   all} {:a  "A"
                   :b  "B"
                   :c  "C"
                   "d" "D"
                   'e  "E"}]
  all)
;=> {:a "A", :b "B", :c "C", "d" "D", e "E"}

;-----------------------------------XXX----------------------------------

(let [[_ {[_ _ [_ _ {hello :hello}]] :v}] [{:something "something"}
                                           {:idk "some-value"
                                            :v   [1 2 [2 4 {:hello "world"}]]}]]
  hello)
;=> "world"

;-----------------------------------XXX----------------------------------

(def my-map {:x 10 :y  20 :z 30})

(let [{x :x y :y z :z} my-map]
  (+ x y z))
;=> 60

(let [{:keys [x y z]} my-map]
  (+ x y z))
;=> 60