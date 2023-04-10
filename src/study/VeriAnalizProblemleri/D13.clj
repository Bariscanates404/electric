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


(get-in  my-map [1 :name])
;=> {:first "ali", :last "veli"}

(let [{:keys [first last]} (get-in  my-map [1 :name])]
  (conj [] first last))
;1 ali veli



(count my-map)
;=> 2


(defn d11 [coll]
  (reduce
    (fn [x y]
      (let [{:keys [first last]} y]
        (conj x (vector first last))
        ))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (get-in  my-map [len :name])
      )))
(d11 my-map)
;=> [["ali" "veli"] ["batu" "can"]]
