(ns study.MantikAnaliziCalismalari.E30
  (:require [clojure.string :as str]))


;date: 20230417
;rfr: src/study/VeriAnalizProblemleri/D16.clj

(def m {1          {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}}
                 2 {:id 2 :city "Ankara" :name {:first "batu" :last "can"}}})




(defn d16 [outer-coll ?s]
  (reduce
    (fn [x y]
      (let [{:keys [id city _]} y]
        (if (str/includes? (str/lower-case city) (str/lower-case ?s))
          (conj x (outer-coll id))
          x)))
    []
    (for [len (range 1 (+ 1 (count outer-coll)))]
      (get-in outer-coll [len])
      )))

(d16 m "ankara")
;=> [{:id 2, :city "Ankara", :name {:first "batu", :last "can"}}]


;-------------------------------------------DEBUGGING------------------------------------------------
;dıştan içe doğru debugging --->


;-------------------------------------------reduce fonskiyonu------------------------------------------------
(def m {1          {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}}
                 2 {:id 2 :city "Ankara" :name {:first "batu" :last "can"}}})
(def ?s "ankara")


(def f (fn [x y]
                (let [{:keys [id city _]} y]
                  (if (str/includes? (str/lower-case city) (str/lower-case ?s))
                    (conj x (m id))
                    x))))
(def reduce-init [])
(def reduce-coll (for [len (range 1 (+ 1 (count m)))]
                   (get-in m [len])
                   ))

(reduce f reduce-init reduce-coll)
;=> [{:id 2, :city "Ankara", :name {:first "batu", :last "can"}}]


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
;=> {:id 1, :city "Istanbul", :name {:first "ali", :last "veli"}}

(get-in m [2])
;=> {:id 2, :city "Ankara", :name {:first "batu", :last "can"}}

;-------------------------------------------reduce-f fonksiyonu------------------------------------------------

;(fn [x y]
;  (let [{:keys [id city _]} y]
;    (if (str/includes? (str/lower-case city) (str/lower-case ?s))
;      (conj x (m id))
;      x)))

;1. adım destructuring yapacağım mapin dizaynı ile ilgili çalışıyorum. Bunu for ile vereceğim bu sebeple 2. seviyeden alıyorum.
(def level-2-map {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}})

(let [{:keys [id city _]} level-2-map]
  (print id city))
;=>  1 Istanbul     sonuç beklediğimiz gibi, destructuringimiz çalışıyor.


;if blogunun benzerlerini daha önce bir çok kez debug ettik bu nedenle biraz daha yüzelsel devam ediyorum.
;=> id 1 city Istanbul
(if (str/includes? (str/lower-case "Istanbul") (str/lower-case ?s))
  (conj [] (m 1))
  [])
;=> []

;=> id 1 city Ankara
(if (str/includes? (str/lower-case "Ankara") (str/lower-case ?s))
  (conj [] (m 1))
  [])
;=> [{:id 1, :city "Istanbul", :name {:first "ali", :last "veli"}}]

;iterasyonlarımıza bakalım
;1. iterasyon
(f reduce-init {:id 1 :city "Istanbul" :name {:first "ali" :last "veli"}})
;=> []

;2. iterasyon
(f reduce-init {:id 2 :city "Ankara" :name {:first "batu" :last "can"}})
;=> [{:id 2, :city "Ankara", :name {:first "batu", :last "can"}}]