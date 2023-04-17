(ns study.MantikAnaliziCalismalari.E27)

;date: 20230417
;rfr: src/study/VeriAnalizProblemleri/D13.clj
(def m {1 {:id   1
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
      (get-in m [len])
      )))


(d11 m)
;=> [["ali" "veli"] ["batu" "can"]]

;-------------------------------------------DEBUGGING------------------------------------------------
(comment
  ;reduce f init coll imzası ile kullanılmış.
  (def m {1 {:id   1
             :name {:first "ali" :last "veli"}}
          2 {:id   2
             :name {:first "batu" :last "can"}}})

  (def f (fn [x y]
           (let [{id :id {first :first, last :last} :name} y]
             (conj x (vector id first last))
             )))
  (def reduce-init [])
  (def reduce-coll (for [len (range 1 (+ 1 (count m)))]
                     (get-in m [len])
                     ))

  (reduce f reduce-init reduce-coll)
  ;=> [[1 "ali" "veli"] [2 "batu" "can"]]


  ;-------------------------------------------reduce-coll fonksiyonundan başlayalım------------------------------------------------
  (for [len
        (range 1
               (+ 1
                  (count m)))]
    (get-in m [len])
    )

  (count m)
  ;=> 2
  (+ 1 2)
  ;=> 3
  (range 1 3)
  ;=> (1 2)  ---> len

  (get-in m [1])
  ;=> {:id 1, :name {:first "ali", :last "veli"}}

  (get-in m [2])
  ;=> {:id 2, :name {:first "batu", :last "can"}}


  ;-------------------------------------------reduce-f fonksiyonu------------------------------------------------
  ;(fn [x y]
  ;  (let [{id :id {first :first, last :last} :name} y]
  ;    (conj x (vector id first last))
  ;    ))

  ;destructuring blogumuzu  debugedelim.
  (let [{id :id {first :first, last :last} :name} {:id 1, :name {:first "ali", :last "veli"}}]
    (print id first last))
  ;1 ali veli

  (conj [] (vector "id" "first" "last"))
  ;=> [["id" "first" "last"]]

  ;iterasyonlarımıza bakalım
  ;1. iterasyon
  (f reduce-init {:id 1, :name {:first "ali", :last "veli"}})
  ;=> [[1 "ali" "veli"]]

  ;2. iterasyon
  (f reduce-init {:id 2, :name {:first "batu", :last "can"}})
  ;=> [[1 "ali" "veli"] [2 "batu" "can"]] ve beklediğimiz sonuca ulaşıyoruz.
  )