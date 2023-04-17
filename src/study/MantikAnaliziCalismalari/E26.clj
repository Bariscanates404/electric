(ns study.MantikAnaliziCalismalari.E26)

;date: 20230417
;rfr: src/study/VeriAnalizProblemleri/D12.clj
;-------------------------------------------DEBUGGING------------------------------------------------
(comment
  (def m [{:id   1
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
  (d11 m)
  ;=> [["ali" "veli"] ["batu" "can"]]

  ;debuggingimize başlayalım;
  ;reduce  f init coll imzası ile kullanılmış.
  (def f (fn [x y]
           (let [{:keys [first last]} y]
             (conj x (vector first last))
             )))
  (def reduce-init [])
  (def reduce-coll (for [len (range 0 (count m))]
                     (get-in (get-in m [len]) [:name])
                     ))

  (reduce f reduce-init reduce-coll)
  ;=> [["ali" "veli"] ["batu" "can"]]

  ;şimdi formlarımızı tek tek debug edelim.
  ;-------------------------------------------f fonksiyonu------------------------------------------------

  (let [{:keys [first last]} (get-in (get-in m [0]) [:name])]
    (conj [] first last))
  ;ali veli

  (get-in (get-in m [0]) [:name])
  ;=> {:first "ali", :last "veli"}

  (count m)
  ;=> 2


  (let [{:keys [first last]} {:first "ali", :last "veli"}]
    (print (vector first last))
    )
  ;==> [ali veli] beklediğimiz sonuç doğru dönüyor.


  ;-------------------------------------------reduce-coll fonksiyonu------------------------------------------------
 ; (def reduce-coll
 ;   (for [len
 ;         (range 0
 ;                (count m))]
 ;     (get-in (get-in m [len]) [:name])
 ;     ))


  (count m)
  ;=> 2
  (range 0 2)
  ;=> (0 1)

  ;reduce fonksiyonuna arguman olarak yollanan collectionlar.
  (get-in (get-in m [0]) [:name])
  ;=> {:first "ali", :last "veli"}
  (get-in (get-in m [1]) [:name])
  ;=> {:first "batu", :last "can"}


  ;şimdi iterasyonlarımızı yapalım

  ;1. iterasyon
  (f reduce-init {:first "ali", :last "veli"})
  ;=> [["ali" "veli"]]
  ;2. iterasyon
  (f reduce-init {:first "batu", :last "can"})
  ;=> [["ali" "veli"] ["batu" "can"]]
  )