(ns study.MantikAnaliziCalismalari.E03)

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

