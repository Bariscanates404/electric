(ns study.MantikAnaliziCalismalari.E24)
;date: 20230417
;rfr: src/study/VeriAnalizProblemleri/D10.clj -> a02


(def m {1      {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             })

(def v (into [] (for [m (vals m) [k v] m] [(name k) v])))
;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]
;20230414 Barış Can Ateş
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

(filter-vector-func v "a")
;=> (["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"])


;1. adım olarak thread last ve threading makrolarımı standart kullanıma dönüştürdüm.
(defn filter-vector-func-a02 [coll search-str]
  (filter
    (partial some
             (fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str))
             )
    coll))

;çalıştığını kontrol ediyoruz.
(filter-vector-func-a02 v "a")
;=> (["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"])


(comment
  (def coll v)
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
  (def coll v)
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
  (def coll v)
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
