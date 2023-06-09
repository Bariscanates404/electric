(ns study.MantikAnaliziCalismalari.E25
  (:require [clojure.string :as str]))
;date: 20230417
;rfr: src/study/VeriAnalizProblemleri/D11.clj

(def m {1      {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             }
  )


;-------------------------------------------d01------------------------------------------------
;==> a01
;STEP 1
(defn filter-map-func-name [coll ?s]
  (reduce
    (fn [x y]
      (let [{:keys [id name surname]} y]
        (if (str/includes? (str/lower-case name) (str/lower-case ?s))
          (conj x (vector id name))
          x)))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (m len)
      )))

(filter-map-func-name m "a")
;=> [[1 "ali"] [2 "batu"]]


;STEP 2
(defn filter-map-func-surname [coll ?s]
  (reduce
    (fn [x y]
      (let [{:keys [id name surname]} y]
        (if (str/includes? (str/lower-case surname) (str/lower-case ?s))
          (conj x (vector id surname))
          x)))
    []
    (for [len (range 1 (+ 1 (count coll)))]
      (m len)
      )))

(filter-map-func-surname m "a")

(defn search-func-d11 [coll ?s]
  (conj (filter-map-func-name coll ?s) (filter-map-func-surname coll ?s))
  )



;-------------------------------------------DEBUGGING------------------------------------------------
;step 1'i debug edelim.
(comment
  (def m {1 {:id 1 :name "ali" :surname "veli"}
          2 {:id 2 :name "batu" :surname "can"}
          })
  (def ?s "a")

  (defn filter-map-func-name [outer-coll ?s]
    (reduce
      (fn [x y]
        (let [{:keys [id name surname]} y]
          (if (str/includes? (str/lower-case name) (str/lower-case ?s))
            (conj x (vector id name))
            x)))
      []
      (for [len (range 1 (+ 1 (count outer-coll)))]
        (m len)
        )))

  ;reduce için f init coll imzasını kullandık
  (def f (fn [x y]
           (let [{:keys [id name surname]} y]
             (if (str/includes? (str/lower-case name) (str/lower-case ?s))
               (conj x (vector id name))
               x))))
  (def reduce-init [])
  (def reduce-coll (for [len (range 1 (+ 1 (count m)))]
                     (m len)
                     ))
  (identity reduce-coll)
  ;=> ({:id 1, :name "ali", :surname "veli"} {:id 2, :name "batu", :surname "can"})


  ;ilk olarak reduce-coll fonskiyonunu debug edeceğim çünkü bu fonksiyonun çıktısı reduce içerisinde kullanılıyor.
  (for [len
        (range 1
               (+ 1 (count m)))]
    (m len)
    )

  (+ 1 (count m))
  ;=> 3

  (for [len (range 1 3)]
    (m len)
    )

  (range 1 3)
  ;=> (1 2)

  (m 1)
  ;=> {:id 1, :name "ali", :surname "veli"}

  (m 2)
  ;=> {:id 2, :name "batu", :surname "can"}



  ;buraya kadar yaptığımız debuggingler ile reduce-coll fonskiyonunun nasıl çalıştığını ayrıntıları ile gözlemledik. Şimdi recude-f fonksiyonuna geçebiliriz.
  (reduce f reduce-init reduce-coll)
  ;=> [[1 "ali"] [2 "batu"]]

  ;---f fonksiyonu----
  (let [{:keys [id name surname]} {:id 1, :name "ali", :surname "veli"}]
    (print id name surname))
  ;==> 1 ali veli

  ;str/includes formunu inceleyelim.
  (str/includes? (str/lower-case "ali") (str/lower-case "a"))
  ;=> true

  ;if içerisinde kullandığımız için str/includes  logic return fonc olarak kullanılıyor.
  (if (str/includes? (str/lower-case "ali") (str/lower-case "a"))
    (conj [] (vector 1 "ali"))
    [])
  ;=> [[1 "ali"]]

  (if (str/includes? (str/lower-case "ali") (str/lower-case "x"))
    (conj [] (vector 1 "ali"))
    [])
  ;=> []


  ;formlarımızın debugginglerini yaptık şimdi iterasyonalra bakalım
  (f reduce-init {:id 1, :name "ali", :surname "veli"})
  ;=> [[1 "ali"]]
  (f reduce-init {:id 2 :name "batu" :surname "can"})
  ;=> [[1 "ali"] [2 "batu"]]


  ;STEP 2 ile step 1 birebir aynı bu sepeble debugging yapmıyorum.
  )