(ns study.MantikAnaliziCalismalari.E29)



;date: 20230417
;rfr: src/study/VeriAnalizProblemleri/D15.clj

(def m {1 {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}}
        2 {:id 2 :city "Ankara" :name {:first "batu" :last "can"}}})

;şimdi reduce ile vector içerisinde toplayalım

(defn d15 [coll]
  (reduce
    (fn [x y]
      (let [{:keys [id city name]} y                        ;1. seviye map - içerisinde bulunan bütün öğelerin pairlerini döner
            {:keys [first last]} name]                      ;2. seviye map - name keyi içerisinde bulunan pairleri döner
        (conj x (vector id city first last))
        ))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (get-in m [len])
      )))
(d15 m)
;=> [[1 "Istanbul" "ali" "veli"] [2 "Ankara" "batu" "can"]]

;-------------------------------------------DEBUGGING------------------------------------------------
;20230417
;dıştan içe doğru debugging --->


;-------------------------------------------reduce fonskiyonu------------------------------------------------
(comment
  (def m {1 {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}}
          2 {:id 2 :city "Ankara" :name {:first "batu" :last "can"}}})


  (def f (fn [x y]
           (let [{:keys [id city name]} y                   ;1. seviye map - içerisinde bulunan bütün öğelerin pairlerini döner
                 {:keys [first last]} name]                 ;2. seviye map - name keyi içerisinde bulunan pairleri döner
             (conj x (vector id city first last))
             )))
  (def reduce-init [])
  (def reduce-coll (for [len (range 1 (+ 1 (count m)))]
                     (get-in m [len])
                     ))

  (reduce f reduce-init reduce-coll)
  ;=> [[1 "Istanbul" "ali" "veli"] [2 "Ankara" "batu" "can"]]


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
  ;  (let [{:keys [id city name]} y                          ;1. seviye map - içerisinde bulunan bütün öğelerin pairlerini döner
  ;        {:keys [first last]} name]                        ;2. seviye map - name keyi içerisinde bulunan pairleri döner
  ;    (conj x (vector id city first last))
  ;    ))

  ;1. adım destructuring yapacağım mapin dizaynı ile ilgili çalışıyorum. Bunu for ile vereceğim bu sebeple 2. seviyeden alıyorum.
  (def level-2-map {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}})

  ;burada nested map destructring yapıyoruz ve istediğimiz valuelara ulaşıyoruz.
  (let [{:keys [id city name]} level-2-map                  ;1. seviye map - içerisinde bulunan bütün öğelerin pairlerini döner
        {:keys [first last]} name]                          ;2. seviye map - name keyi içerisinde bulunan pairleri döner
    (print id city first last))
  ;=>  1 Istanbul ali veli      sonuç beklediğimiz gibi, destructuringimiz çalışıyor.


  (conj [] (vector 1 "city" "first" "last"))
  ;=> [[1 "city" "first" "last"]]

  ;iterasyonlarımıza bakalım
  ;1. iterasyon
  (f reduce-init {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}})
  ;=> [[1 "Istanbul" "ali" "veli"]]

  ;2. iterasyon
  (f reduce-init {:id 2 :city "Ankara" :name {:first "batu" :last "can"}})
  ;=> [[2 "Ankara" "batu" "can"]] ve beklediğimiz sonuca ulaşıyoruz.
  )