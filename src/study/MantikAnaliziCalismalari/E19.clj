(ns study.MantikAnaliziCalismalari.E19)


(def my-vec [1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             ])


(mapv

  (fn [o] (if (map? o)
            ((juxt :name :surname) o) o)
    )
  my-vec
  )


;burada fonksiyonu debug etmek istiyorum
#_((fn [o] (if (map? o)
          ((juxt :name :surname) o) o)
  )
 '("collection-here")
 )

;şu şekilde çalışıyor,verdiğimiz collectionun ilk elementinden son elementine doğru tektek fonksiyona verir.
;burada şuna dikkat eder verdiği elementin map mi değil mi diye sorar eğer map ise  juxt  fonksiyonuna uygular
;değil ise o haliyle bırakır. şimdi iterationlara bakalım


;tur 1
((fn [o] (if (map? o)
           ((juxt :name :surname) o) o)
   )
 1
 )
;=> (1)

;tur 2
((fn [o] (if (map? o)
           ((juxt :name :surname) o) o)
   )
 {:id 1 :name "ali" :surname "veli"}
 )
;=> ["ali" "veli"] map olduğu için juxt çalıştı

;tur 3
((fn [o] (if (map? o)
           ((juxt :name :surname) o) o)
   )
 2
 )
;=> (2)

;tur 4
((fn [o] (if (map? o)
           ((juxt :name :surname) o) o)
   )
 {:id 2 :name "batu" :surname "can"}
 )
;=> ["batu" "can"]
;burada iterationlarımız bitti ve sonucu aldık.