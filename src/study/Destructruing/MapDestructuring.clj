(ns study.Destructruing.MapDestructuring)

;güzel bir kaynak ------>> https://gist.github.com/john2x/e1dca953548bfdfb9844




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








;; NESTED DEEP MAPS ;;

(def my-nested-map-3 {:id 1 :name {:first "ali" :last "veli"} :surname {:snf "foo" :snl "bar" :location {:country "usa"}} :age 26})
;burada uzun bir şekilde yapılmış bir destruction görüyoruz.

(let [{:keys [id age] {:keys [first last]} :name {:keys [snf snl], {:keys [country]} :location} :surname}
      my-nested-map-3]
  (print id age first last snf snl country))


(let [{:keys [id name surname age]} my-nested-map-3  ;1. seviye map - içerisinde bulunan bütün öğelerin pairlerini döner
      {:keys [first last]} name ;2. seviye map - name keyi içerisinde bulunan pairleri döner
      {:keys [snf snl location]} surname ;  2. seviye map - surname keyi içerisinde bulunan pairleri döner
      {:keys [country]} location] ;3. seviye map - location keyi  içeirsinde bulunan pairleri döndürür
  (print id age first last snf snl country))





;plumbing library kullanarak destructuring yapmak==>
(use '[plumbing.core])
((let [data {:id      1 :name {:first "ali" :last "veli"}
             :surname {:snf "foo" :snl "bar" :location {:country "usa"}}
             :age     26}]
   (let [[id name surname] data
         [first last] name
         [snf snl location] surname
         [country] location]
     country)
   )
 )
