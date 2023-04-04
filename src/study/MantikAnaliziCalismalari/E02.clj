(ns study.MantikAnaliziCalismalari.E02)



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