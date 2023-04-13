(ns study.VeriAnalizProblemleri.D10
  (:require
    [clojure.string :as str]
    [study.reusable-functions :as rf]))

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


;a01 ==>
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


;a02===>
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

;04/13/2023
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

  ;debugging için yaptığımız parçalamanın doğru çalıştığını gördük. şimdi daha derinlemesine kodlarımızı parçalayarak debugging yapalım.
  ;some fonksiyonu pred ve coll argumanlarını alıyor. pred fonksiyonunda yapılan logic değerlendirme eğer collection içerisinde birkere bile karşılanıyorsa
  ; true döner karşılanmıyorsa bütün collection da karşılanmıyorsa nil döner.
  ;partial ile bir fonksiyona kısmı bir argüman vermeye yarar.
  (def search-str "a")
  (partial some
           (fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str))
           )

  (def search-str "a")
  (def coll my-vec)
  (identity coll)
  ;=> [["id" 1] ["name" "ali"] ["surname" "veli"] ["id" 2] ["name" "batu"] ["surname" "can"]]

  ;şimdi partial some fonskiyonun nasıl kullanıldığına bir göz atalım sonra devamında daha derinlemesine debugging yapacağım.
  (some (fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) coll)
  ;=> true
  ((fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) coll)
  ;=> true

  ;görüldüğü üzere search-str değişkenine verdiğimiz metin yada karakterleri collection içerisinde verilen vector çiftleri içerisinde arar, eğer bulursa
  ;eğer bulursa true döner. fakat burada zaten includes? bize true döndürdüğü için tekrardan true döndürmek zorunda değiliz. partial some formunu fazladan kullanmışım.
  ;bu formu hazırladığım fonksiyondan çıkartınca dahi beklendiği gibi çalışıyor.

  ;şimdi daha derinlemesine debugging yapalım. Fonksiyonumuzun içerisine girelim.

  ;fonksinumuzun içerisindeki son formumuzu  debug edelim. verilen ikinci string argumanın birinci string argumanın içerisinde var olup olmadığı kontrol eder.
  ;var ise true yok ise false döner.
  (clojure.string/includes? (clojure.string/lower-case "merhaba") "merh")


  (def search-str "a")
  (def coll my-vec)
  ;şimdi str-vec collection dan buraya gelen vector çiftleriydi. bu iterasyonu filter fonksiyonu yapıyor bu nedenle burada iterasyonları el ile manuel yolarak yapcağım.
  ;1. iterasyon ==>
  (def str-vec ["id" 1])
  ((fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) str-vec)
  ;=> false
  ;Filter fonksiyonu sonuçları seq içerisinde saklar
  ;sonuç seq => ()

  ;1. iterasyonda false sonuç döndü bu nedenle filter fonksiyonu o sonucu görmezden gelir hiç birşey yapmaz.
  ;2. iterasyon ==>
  (def str-vec ["name" "ali"])
  ((fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) str-vec)
  ;=> true
  ;2. iterasyonda fonksiyonumuz true döndü. Bu nedenle filter fonksiyonu elementi sonuç listesie ekler.
  ;sonuç seq => (["name" "ali"])


  ;3. iterasyon ==>
  (def str-vec ["surname" "veli"])
  ((fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) str-vec)
  ;=> true
  ;3. iterasyonda fonksiyonumuz true döndü. Bu nedenle filter fonksiyonu elementi sonuç listesie ekler.
  ;sonuç seq => (["name" "ali"] ["surname" "veli"])

  ;4. iterasyon ==>
  (def str-vec ["id" 2])
  ((fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) str-vec)
  ;=> false
  ;4. iterasyonda false sonuç döndü bu nedenle filter fonksiyonu o sonucu görmezden gelir hiç birşey yapmaz.
  ;sonuç seq => (["name" "ali"] ["surname" "veli"])

  ;5. iterasyon ==>
  (def str-vec ["name" "batu"])
  ((fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) str-vec)
  ;=> true
  ;5. iterasyonda fonksiyonumuz true döndü. Bu nedenle filter fonksiyonu elementi sonuç listesie ekler.
  ;sonuç seq => (["name" "ali"] ["surname" "veli"] ["name" "batu"])

  ;6. iterasyon ==>
  (def str-vec ["surname" "can"])
  ((fn [str-vec] (clojure.string/includes? (clojure.string/lower-case str-vec) search-str)) str-vec)
  ;=> true
  ;6. iterasyonda fonksiyonumuz true döndü. Bu nedenle filter fonksiyonu elementi sonuç listesie ekler.
  ;sonuç seq => (["name" "ali"] ["surname" "veli"] ["name" "batu"] ["surname" "can"])

  ;6. iterasyondan sonra colldaki bütün elementler filtre edilmiş oluyor ve sonuç listesi bize geri döndürülüyor.
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