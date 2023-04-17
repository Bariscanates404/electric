(ns study.VeriAnalizProblemleri.D10
  (:require
    [clojure.string :as str]
    [study.reusable-functions :as rf]))
;Debugging ref ->
;rfr: src/study/MantikAnaliziCalismalari/E23.clj -> a01
;rfr: src/study/MantikAnaliziCalismalari/E24.clj -> a02


;Girdi:
;
;```clojure
;{1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}}
;```
;
;Arama anahtar kelimesi:
;
;```
;"a"
;```
;
;Bu anahtar kelimeyi tüm property'lerde arayın. Eşleşen property'leri key-value ikilileri olarak dönün.
;
;Çıktı:
;
;```clojure
;[["name" "ali"] ["name" "batu"] ["surname" "can"]]
;```


(def my-map {1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             })

(def my-vec (into [] (for [m (vals my-map) [k v] m] [(name k) v])))
;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

;-------------------------------------------d01------------------------------------------------
;a01 ==> burada standart reduce fonksiyonu ile yaptık.
(defn filter-vector-func2 [coll ?s]
  (reduce
    (fn [x y]
      (let [[:as all] y]
        (if (str/includes? (str/lower-case all) (str/lower-case ?s))
          (conj x all)
          x)))
    []
    coll))

(comment

  (def ?s "x")
  (def f (fn [x y]
           (let [[:as all] y]
             (if (str/includes? (str/lower-case all) (str/lower-case ?s))
               (conj x all)
               x))))


  (def y ["name" "ali"])

  (f [] y)

  ((fn [x y]
     (let [[:as all] y]
       (if (str/includes? (str/lower-case all) (str/lower-case ?s))
         (conj x all)
         x)))
   []
   ["name" "ali"]
   )

  (fn []
    (let [[:as all] ["name" "ali"]]
      (if (str/includes? (str/lower-case all) (str/lower-case ?s))
        (conj [] all)
        [])))

  (if (str/includes? (str/lower-case "ali") (str/lower-case "a"))
    (conj [] "ali")
    [])
  ;=> ["ali"]
  (if (str/includes? (str/lower-case "veli") (str/lower-case "a"))
    (conj [] "ali")
    [])
  ;=> ["veli" "ali"]



  (def ?s "a")
  (let [[:as all] ["name" "ali"]]
    (if (str/includes? (str/lower-case all) (str/lower-case ?s))
      (conj [] all)
      []))
  ;==> [["name" "ali"]]

  (def ?s "x")
  (let [[:as all] ["name" "ali"]]
    (if (str/includes? (str/lower-case all) (str/lower-case ?s))
      (conj [] all)
      []))
  ;==> []

  (if (str/includes? (str/lower-case ["ali" "veli"]) (str/lower-case "a"))
    (conj [] ["ali" "veli"])
    [])
  ;=> [["name" "ali"]]

  (str/lower-case ["ali" "veli"])
  ;=> "[\"ali\" \"veli\"]"

  )




(filter-vector-func2 my-vec "a")
;[["name" "ali"] ["name" "batu"] ["surname" "veli"] ["surname" "can"]]

(filter-vector-func2 my-vec "v")
;=> [["surname" "veli"]]



(let [[first second :as all] my-vec]
  all)

;-------------------------------------------d01------------------------------------------------
;a02===> e01 e göre extra bir filter fonksşyonu ve threading makroları kullandım.
(defn filter-vector-func [coll search-str]
  (->> coll
       (filter (fn [str-vec]
                 (-> str-vec
                     clojure.string/lower-case
                     (clojure.string/includes? search-str)))

               )
       )
  )

(filter-vector-func my-vec "a")

;20230414
;1. adım olarak thread last ve threading makrolarımı standart kullanıma dönüştürdüm.
(defn filter-vector-func-a02 [coll search-str]
  (filter
    (partial some
             (fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str))
             )
    coll))

;çalıştığını kontrol ediyoruz.
(filter-vector-func-a02 my-vec "a")
;=> (["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"])


(comment
  (def coll my-vec)
  (def search-str "a")
  ;şimdi debuggingimize başlayabiliriz.en dış formda filter fonksiyonumuz var. bu fonksiyonu "pred coll" imzası ile kullanıyoruz.

  (def pred (partial some
                     (fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str))
                     ))

  ;şimdi debuggingimizi doğru yaptığımızı kontrol etmek için kodumuzu bir çalıştıralım.
  (filter pred coll)
  ;=> (["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"])

  (pred ["id" 1])

  ;debugging için yaptığımız parçalamanın doğru çalıştığını gördük. şimdi daha derinlemesine kodlarımızı parçalayarak debugging yapalım.
  ;some fonksiyonu pred ve coll argumanlarını alıyor. pred fonksiyonunda yapılan logic değerlendirme eğer collection içerisinde birkere bile karşılanıyorsa
  ; true döner karşılanmıyorsa bütün collection da karşılanmıyorsa nil döner.
  ;partial ile bir fonksiyona kısmı bir argüman vermeye yarar.
  (def search-str "ali")
  (filter pred coll)
  ;=> (["name" "ali"])
  (def search-str "name")
  (filter pred coll)
  ;=> (["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"])

  (def search-str "a")
  (def coll my-vec)
  (identity coll)
  ;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

  ;şimdi partial some fonskiyonun nasıl kullanıldığına bir göz atalım sonra devamında daha derinlemesine debugging yapacağım.
  (some (fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) coll)
  ;=> true
  ((fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) coll)
  ;=> true
  (def pred (fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)))
  ;==> artık pred functionumuzdan some'ı çıkartıyoruz.

  ;En iç fonksişyonumuzun çalışması
  (clojure.string/includes? (clojure.string/lower-case "merhaba") "merh")
  ;=> true

  (def search-str "a")
  (def coll my-vec)
  ;şimdi str-vec collection dan buraya gelen vector çiftleriydi. bu iterasyonu filter fonksiyonu yapıyor bu nedenle burada iterasyonları el ile manuel yolarak yapcağım.
  ;1. iterasyon ==>
  (def str-vec ["id" 1])
  (pred str-vec)
  ;=> false

  ;2. iterasyon ==>
  (def str-vec ["name" "ali"])
  (pred str-vec)
  ;=> true

  ;3. iterasyon ==>
  (def str-vec ["surname" "veli"])
  (pred str-vec)
  ;=> true

  ;4. iterasyon ==>
  (def str-vec ["id" 2])
  (pred str-vec)
  ;=> false

  ;5. iterasyon ==>
  (def str-vec ["name" "batu"])
  (pred str-vec)
  ;=> true

  ;6. iterasyon ==>
  (def str-vec ["surname" "can"])
  (pred str-vec)
  ;=> true
  ;(["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"])

  )













(defn filter-vector-func-by-postwalk [coll search-str]
  (->> coll
       (clojure.walk/postwalk
         (fn [form]
           (let [form (if (vector? form)
                        (vec (keep identity form))
                        form)]
             (cond
               (and (vector? form)
                    (every? vector? form)) form
               (and (vector? form)
                    (not (every? string? form))) nil
               (and (vector? form)
                    (every? string? form)
                    (->> form
                         (some (fn [str-vec]
                                 (-> str-vec
                                     clojure.string/lower-case
                                     (clojure.string/includes? search-str)))))) form
               :else form))))
       (keep identity)
       vec))

(filter-vector-func-by-postwalk my-vec "a")