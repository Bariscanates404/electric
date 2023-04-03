(ns study.MantikAnaliziCalismalari.E01)

(reduce + (filter odd? (map #(+ 2 %) (range 0 10))))

(comment
  ;Burada yukarıdaki işlemleri sırasıyla ich comment blogu içerisinde inceleyelim.
  (range 0 10)
  ;=> (0 1 2 3 4 5 6 7 8 9)
  #(+ 2 %)
  ;bu işlem anonim fonksiyon olduğu için tek başına çalışmaz.
  (#(+ 2 %) 3)
  ;=> 5
  (map #(+ 2 %) (range 0 10))
  ;=> (2 3 4 5 6 7 8 9 10 11)
  (filter odd? (map #(+ 2 %) (range 0 10)))
  ;=> (3 5 7 9 11)
  (reduce + '(3 5 7 9 11))
  ;=> 35

  ;1- 0 dan 10 a kadar sayıları al
  ;2- bu sayılara +2 ekle
  ;3- oluşan yeni listedeki tek sayıları filtrele
  ;4- bunları topla   =35
  )


(def xform
  (comp
    (partial filter odd?)
    (partial map #(+ 2 %))))

(reduce + (xform (range 0 10)))
; 35


(comment
  (range 0 10)
  ;=> (0 1 2 3 4 5 6 7 8 9)
  ;((comp str +) 8 8 8) bu örnekten görebileceğimiz gibi comp fonksiyonu öncelikle sağdan başlar ve
  ;sola doğru fonksiyonları uygular. bu sebeple 1. olarak = (partial map #(+ 2 %)) bu fonksiyon çalışacak
  ; 2. olarak = (partial filter odd?) bu fonksiyon çalışacak.
  (partial map #(+ 2 %) '(0 1 2 3 4 5 6 7 8 9))
  ;partial bir fonksiyonda kullanıldığında ilk argüman olarak bir değerin verilmesini sağlar ve verilmesi gereken argman sayısından
  ;en az bir eksik argüman ile fonksiyonu çağırmamıza yardımcı olur. Bu nedenle burada partiali kaldırmamız gerekiyor debuggign yaparken.
  (map #(+ 2 %) '(0 1 2 3 4 5 6 7 8 9))
  ;=> (2 3 4 5 6 7 8 9 10 11)
  ;comp sağdan sola doğru çalışırdı, bu neden ikinci fonksiyon olan filter fonksiyonunun çalışma sırası geliyor.
  (partial filter odd? '(2 3 4 5 6 7 8 9 10 11))
  ;partial olduğu için sonuç dönmüyor partialı kaldırıyorum.
  (filter odd? '(2 3 4 5 6 7 8 9 10 11))
  ;=> (3 5 7 9 11)
  ;şimdi (xform (range 0 10)) bu fonksiyonu çözümlemiş olduk. Sırada reduce + fonksiyonu var.
  (reduce + '(3 5 7 9 11))
  ;=> 35

  )


;transduce fonksiyonları
;; First, define a transducer for producing the first ten odd numbers:
(def xf (comp (filter odd?) (take 10)))

;; We can then apply this transducer in different ways using transduce.

;; Get the numbers as a sequence:

(transduce xf conj (range))
;;=> [1 3 5 7 9 11 13 15 17 19]

;; Or sum them:

(transduce xf + (range))
;; => 100

;; ... with an initializer:

(transduce xf + 17 (range))
;; => 117


;(def xf (comp (filter odd?) (take 10)))
(comment
  ;burada xf fonksiyonunu parçalayalım
  ;öncelikle composition içerisine filter fonksiyonu ve take fonksiyonunu dahil etmiş,
  ;comp'un sağdan sola doğru çalıştığını hatırlayalım.
  ;burada dikkat etmemiz gereken en önemli durum, comp fonksiyonları eğer transuder içerisinde kullanılıyorsa
  ;fonksiyonlar soldan sağa doğru çalışır.
  (filter odd?)
  ;bu nedenle ilk olarak filter fonksşyonu çalışır. ve filtrelediği sayıları take fonksiyonuan verir.
  (take 10)
  ;filter fonksiyonundan gelen verilerden ilk 10 tanesini alır.
  )


(comment
  (defn flatten-one-level [coll]
    (mapcat #(if (sequential? %) % [%]) coll))
  ;map fonksiyon ve  coll alıyor.
  (def f #(if (sequential? %) % [%]))
  ;burada anonim fonksiyon verilen argümanı alıyor ve squential olup olmadığını kontrol
  ;ediyor. Eğer sequential ise direk olarak kendisini geri döndürüyor değil ise
  ;vektör içerisine koyarak döndürüyor.
  (def coll [[1 2 [4 5 [6]] [3]]])
  (mapcat)
  ;mapcat apply concat ile map in birleşimidir.
  (map f coll)
  ;=> (1 2 [4 5 [6]] [3]) eğer apply concat kullanmadan map kullanırsak gördüğümüz gibi istediğimiz
  ;sonuca ulaşamıyoruz.
  (apply concat (map f coll))
  ;=> ([1 2 [4 5 [6]] [3]])
  )


(comment
  (def given-map {:a 1 :b 2 :c nil})

  (merge
    (for [[k v] given-map
          :when
          (not (nil? v))] {k v}))
  ;öncelikle bir kod bloğunu parçalarken en dıştan içer doğru formlarına ayırmak
  ;ve ayrılmış formları da en son noktasına kadar parçalamak gerekiyor.

  ;öncelikle for loop forumu inceleyelim.
  (for [[k v] given-map
        :when
        (not (nil? v))] {k v})
  ;=> ({:a 1} {:b 2})
  (not (nil? "not nill value as given args"))
  ;=> true
  (not (nil? nil))
  ;=> false
  ;şimdi for loop'u muzu şu şekilde yazabiliriz.
  (for [[k v] given-map
        :when
        true] {k v})
  ;=> ({:a 1} {:b 2} {:c nil})  buradan true degeri verdiğimiz formun nil değerleri ayıklayan
  ;önemli bir kod olduğunu görüyoruz. Eğer direkt olarak True verirsek destructure

  (mapv
    (fn [data] [(:id data) (:name data) (:surname data)]) (for [lenght (range 1 (+ 1 (count my-map)))]
                                                            (my-map lenght)
                                                            ))



  )