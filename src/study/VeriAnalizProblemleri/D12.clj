(ns study.VeriAnalizProblemleri.D12
  (:require
    [clojure.string :as str]
    [study.reusable-functions :as rf]))
;Debugging ref ->
;rfr: src/study/MantikAnaliziCalismalari/E26.clj


;Girdi:
;
;```clojure
;[{:id 1
;:name {:first "ali" :last "veli"}
; {:id 2
;:name {:first "batu" :last "can"}}}
;```
;
;Çıktı:
;
;```clojure
;[["ali" "veli"]
; ["batu" "can"]]
;```



(def my-vec [{:id   1
              :name {:first "ali" :last "veli"}}
             {:id   2
              :name {:first "batu" :last "can"}}]
  )


(let [{:keys [first last]} (get-in (get-in my-vec [0]) [:name])]
  (conj [] first last))
;1 ali veli

(get-in (get-in my-vec [0]) [:name])
;=> {:first "ali", :last "veli"}

(count my-vec)
;=> 2


(defn d11 [coll]
  (reduce
    (fn [x y]
      (let [{:keys [first last]} y]
        (conj x (vector first last))
        ))
    []
    (for [len (range 0 (count coll))]
      (get-in (get-in coll [len]) [:name])
      )))
(d11 my-vec)
;=> [["ali" "veli"] ["batu" "can"]]

;-------------------------------------------DEBUGGING------------------------------------------------
(def outer-coll [{:id   1
                  :name {:first "ali" :last "veli"}}
                 {:id   2
                  :name {:first "batu" :last "can"}}])
(def ?s "a")


(defn d11 [outer-coll]
  (reduce
    (fn [x y]
      (let [{:keys [first last]} y]
        (conj x (vector first last))
        ))
    []
    (for [len (range 0 (count outer-coll))]
      (get-in (get-in outer-coll [len]) [:name])
      )))
(d11 outer-coll)
;=> [["ali" "veli"] ["batu" "can"]]

;debuggingimize başlayalım;
;reduce  f init coll imzası ile kullanılmış.
(def reduce-f (fn [x y]
                (let [{:keys [first last]} y]
                  (conj x (vector first last))
                  )))
(def reduce-init [])
(def reduce-coll (for [len (range 0 (count outer-coll))]
                   (get-in (get-in outer-coll [len]) [:name])
                   ))

(reduce reduce-f reduce-init reduce-coll)
;=> [["ali" "veli"] ["batu" "can"]]

;şimdi formlarımızı tek tek debug edelim.
;-------------------------------------------f fonksiyonu------------------------------------------------

(let [{:keys [first last]} (get-in (get-in outer-coll [0]) [:name])]
  (conj [] first last))
;ali veli

(get-in (get-in outer-coll [0]) [:name])
;=> {:first "ali", :last "veli"}

(count outer-coll)
;=> 2


(let [{:keys [first last]} {:first "ali", :last "veli"}]
  (print (vector first last))
  )
;==> [ali veli] beklediğimiz sonuç doğru dönüyor.


;-------------------------------------------reduce-coll fonksiyonu------------------------------------------------
(def reduce-coll
  (for [len
        (range 0
               (count outer-coll))]
    (get-in (get-in outer-coll [len]) [:name])
    ))


(count outer-coll)
;=> 2
(range 0 2)
;=> (0 1)

;reduce fonksiyonuna arguman olarak yollanan collectionlar.
(get-in (get-in outer-coll [0]) [:name])
;=> {:first "ali", :last "veli"}
(get-in (get-in outer-coll [1]) [:name])
;=> {:first "batu", :last "can"}


;şimdi iterasyonlarımızı yapalım

;1. iterasyon
(reduce reduce-f reduce-init [{:first "ali", :last "veli"}])
;=> [["ali" "veli"]]
;2. iterasyon
(reduce reduce-f reduce-init [{:first "batu", :last "can"}])
;=> [["ali" "veli"] ["batu" "can"]]

