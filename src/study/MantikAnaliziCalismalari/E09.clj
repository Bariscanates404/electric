(ns study.MantikAnaliziCalismalari.E09)

(def my-vec [-1 -2 -3 -4 5 6])
(defn index-of-pred
  [pred coll]
  (ffirst
    (filter
      (comp pred second)
      (map-indexed list coll)
      )))

(index-of-pred  <  my-vec)

;bu fonksiyon bir vector içerisinde bulunan ilk pozitif değeri döndürür
;şimdi formlarına ayırıp nasıl çalıştığını görelim.


(def coll (map-indexed list my-vec))
(identity coll)
;=> ((0 -1) (1 -2) (2 -3) (3 -4) (4 5) (5 6))

;şimdi coll a atadığımız coll oluşturan fonksiyonu debug edelim.
(map-indexed list my-vec)
;map-indexed fonksiyonunu burada  f coll imzasi ile çalışıyor. my-vec collectionundan aldığı argumanları  liste içerisine map-indexed in oluşturduğu index numaraları ile
;çiftler halinde koyuyor.



;------------------------------------------------------------------------------------------------------------------------------;

(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             })

(def my-vec (into [] (for [m (vals my-map) [k v] m] [(name k) v])))
;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]
