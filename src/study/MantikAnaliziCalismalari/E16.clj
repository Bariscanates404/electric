(ns study.MantikAnaliziCalismalari.E16)

;öncelikle fonksşyon oluşturmusuz onu sadeleştirelim.
(defn filter-odd
  [vals]
  (reduce
    (fn [cum item]                                          ;function
      (if (odd? item)
        (conj cum item)                                     ; if odd, append to cum result
        cum                                                 ; if even, leave cum result unchanged
        ))
    []                                                      ; init value for `cum`
    vals                                                    ; sequence to reduce over
    ))

;burada kodlarımızı fonksiyondan çıkarttık ve debugging yapmaya başlıyoruz.
(reduce
  (fn [cum item]                                            ;function
    (if (odd? item)
      (conj cum item)                                       ; if odd, append to cum result
      cum                                                   ; if even, leave cum result unchanged
      ))
  []                                                        ; init value for `cum`
  (range 0 10)                                              ; sequence to reduce over
  )

;öncelikle debugginge en dış katmandan başlayalım.
;1. reduce fonksiyonu kullanılmış.
;f val coll imzası ile kullanılmış.
;o zaman f i def ile bağlayabiliriz.
(def f (fn [cum item]                                       ;function
         ; Decide how to modify `cum` given the current item
         (if (odd? item)
           (conj cum item)                                  ; if odd, append to cum result
           cum                                              ; if even, leave cum result unchanged
           )))
;init value olarak boş vector atanmış onuda tanımlayalım.
(def init [])
;coll oluşturalım
(def coll (range 0 10))

;kodumuz bu şekilde çalışıyor. şimdi formlarına ayıralım ve derinlemesine debugging yapalım.
(reduce f init coll)
;=> [1 3 5 7 9]

((fn [cum item]                                             ; fonksiyonumuz iki arguman alıyor 1. init yani boş vector. Odd olan sayıları burada toplayacağız. 2. test edielcek sayı. örneğin 1.
   (if (odd? item)                                          ; item a atanmış olan sayı tek mi cift mi kontrol ediyoruz. Eğer tek ise (conj cum item) bu block çalışıyor. çift ise cum blogu çalışıyor.
     (conj cum item)                                        ; item eğer tek ise vectorün içerisini conj ile atıyoruz.
     cum                                                    ; eğer çift ise sonucu değiştirmeden öyle bırakıyoruz.
     )) [] 2)
;   1  => [1]
;   2  => []

;şimdi iterasyonalra bakalım ==>
; inputumuz= '( 1 2 3 4 5)
; 1. tur
((fn [cum item]                                              ;function
   (if (odd? item)
     (conj cum item)                                         ; if odd, append to cum result
     cum                                                     ; if even, leave cum result unchanged
     )) [] 1)
;   1  => [1]

; 2. tur
((fn [cum item]                                              ;function
  (if (odd? item)
    (conj cum item)                                         ; if odd, append to cum result
    cum                                                     ; if even, leave cum result unchanged
    )) [] 2)
;   2  => [1] degismedi

; 3. tur
((fn [cum item]                                              ;function
  (if (odd? item)
    (conj cum item)                                         ; if odd, append to cum result
    cum                                                     ; if even, leave cum result unchanged
    )) [] 3)
;   3  => [1 3]

; 4. tur
((fn [cum item]                                              ;function
  (if (odd? item)
    (conj cum item)                                         ; if odd, append to cum result
    cum                                                     ; if even, leave cum result unchanged
    )) [] 4)
;   4  => [1 3] degismedi

; 5. tur
((fn [cum item]                                             ;function
   (if (odd? item)
     (conj cum item)                                        ; if odd, append to cum result
     cum                                                    ; if even, leave cum result unchanged
     )) [] 5)
;   5  => [1 3 5]



