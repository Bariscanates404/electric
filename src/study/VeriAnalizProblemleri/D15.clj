(ns study.VeriAnalizProblemleri.D15)
;Debugging ref ->
;rfr: src/study/MantikAnaliziCalismalari/E29.clj


;## d15
;
;Girdi:
;
;```clojure
;{1 {:id 1
;  :city "Istanbul"
;  :name {:first "ali" :last "veli"}}
; 2 {:id 2
;  :city "Ankara"
;  :name {:first "batu" :last "can"}}}
;```
;
;Çıktı:
;
;```clojure
;[[1 "Istanbul" "ali" "veli"]
; [2 "Ankara" "batu" "can"]]


(def my-map {1 {:id   1 :city "Istanbul" :name {:first "ali" :last "veli"}}
             2 {:id   2 :city "Ankara" :name {:first "batu" :last "can"}}})



;1. adım destructuring yapacağım mapin dizaynı ile ilgili çalışıyorum. Bunu for ile vereceğim bu sebeple 2. seviyeden alıyorum.
(def level-2-map {:id   1 :city "Istanbul" :name {:first "ali" :last "veli"}})

;burada nested map destructring yapıyoruz ve istediğimiz valuelara ulaşıyoruz.
(let [{:keys [id city name]} level-2-map  ;1. seviye map - içerisinde bulunan bütün öğelerin pairlerini döner
      {:keys [first last]} name] ;2. seviye map - name keyi içerisinde bulunan pairleri döner
  (print id city first last))

;şimdi reduce ile vector içerisinde toplayalım

(defn d15 [coll]
  (reduce
    (fn [x y]
      (let [{:keys [id city name]} y  ;1. seviye map - içerisinde bulunan bütün öğelerin pairlerini döner
            {:keys [first last]} name] ;2. seviye map - name keyi içerisinde bulunan pairleri döner
        (conj x (vector id city first last))
        ))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (get-in my-map [len])
      )))
(d15 my-map)
;=> [[1 "Istanbul" "ali" "veli"] [2 "Ankara" "batu" "can"]]