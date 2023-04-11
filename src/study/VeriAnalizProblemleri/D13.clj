(ns study.VeriAnalizProblemleri.D13)


;Girdi:
;
;```clojure
;{1 {:id 1
;    :name {:first "ali" :last "veli"}}
; 2 {:id 2
;    :name {:first "batu" :last "can"}}}
;```
;
;Çıktı:
;
;```clojure
;[[1 "ali" "veli"]
; [2 "batu" "can"]]
;```

(def my-map {1 {:id   1
                :name {:first "ali" :last "veli"}}
             2 {:id   2
                :name {:first "batu" :last "can"}}})


(get-in my-map [1])
;=> {:first "ali", :last "veli"}

(let [{:keys [first last]} (get-in my-map [1 :name])]
  (conj [] first last))
;1 ali veli



(count my-map)
;=> 2


(def my-nested-hashmap {:a "A" :b "B" :c "C" :d "D" :q {:x "X" :y "Y" :z "Z"}})

(let [{a :a, b :b, {x :x, y :y} :q} my-nested-hashmap]
  (println a b x y))
;; => A B X Y

(def my-nested-map-2 {:id 1 :name {:first "ali" :last "veli"}})

(let [{id :id {first :first, last :last} :name} my-nested-map-2]
  (println id first last))

(def my-nested-map-3 {:id 1 :name {:first "ali" :last "veli"} :surname {:snf "foo" :snl "bar" :location {:country "usa"}} :age 26})



(def !map-id (atom 1))
(defn d11 [coll]
  (reduce
    (fn [x y]
      (let [{id :id {first :first, last :last} :name} y]
        (conj x (vector id first last))
        ))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (get-in my-map [len])
      )))


(d11 my-map)
;=> [["ali" "veli"] ["batu" "can"]]

