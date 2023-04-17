(ns study.VeriAnalizProblemleri.D13)
;Debugging ref ->
;rfr: src/study/MantikAnaliziCalismalari/E27.clj

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


;-------------------------------------------DEBUGGING------------------------------------------------
;reduce f init coll imzası ile kullanılmış.
(def outer-coll {1 {:id   1
                    :name {:first "ali" :last "veli"}}
                 2 {:id   2
                    :name {:first "batu" :last "can"}}})

(def reduce-f (fn [x y]
                (let [{id :id {first :first, last :last} :name} y]
                  (conj x (vector id first last))
                  )))
(def reduce-init [])
(def reduce-coll (for [len (range 1 (+ 1 (count outer-coll)))]
                   (get-in outer-coll [len])
                   ))

(reduce reduce-f reduce-init reduce-coll)
;=> [[1 "ali" "veli"] [2 "batu" "can"]]


;-------------------------------------------reduce-coll fonksiyonundan başlayalım------------------------------------------------
(for [len
      (range 1
             (+ 1
                (count outer-coll)))]
  (get-in outer-coll [len])
  )

(count outer-coll)
;=> 2
(+ 1 2)
;=> 3
(range 1 3)
;=> (1 2)  ---> len

(get-in outer-coll [1])
;=> {:id 1, :name {:first "ali", :last "veli"}}

(get-in outer-coll [2])
;=> {:id 2, :name {:first "batu", :last "can"}}


;-------------------------------------------reduce-f fonksiyonu------------------------------------------------
(fn [x y]
  (let [{id :id {first :first, last :last} :name} y]
    (conj x (vector id first last))
    ))

;destructuring blogumuzu  debugedelim.
(let [{id :id {first :first, last :last} :name} {:id 1, :name {:first "ali", :last "veli"}}]
  (print id first last) )
;1 ali veli

(conj [] (vector "id" "first" "last"))
;=> [["id" "first" "last"]]

;iterasyonlarımıza bakalım
;1. iterasyon
(reduce reduce-f reduce-init [{:id 1, :name {:first "ali", :last "veli"}}])
;=> [[1 "ali" "veli"]]

;2. iterasyon
(reduce reduce-f reduce-init [{:id 2, :name {:first "batu", :last "can"}}])
;=> [[1 "ali" "veli"] [2 "batu" "can"]] ve beklediğimiz sonuca ulaşıyoruz.

