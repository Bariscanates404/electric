(ns study.MantikAnaliziCalismalari.E01)
(use 'clojure.walk)

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


