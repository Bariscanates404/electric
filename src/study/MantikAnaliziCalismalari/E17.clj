(ns study.MantikAnaliziCalismalari.E17)


(def my-vec [{:id 1 :name "ali" :surname "veli"}
             {:id 2 :name "batu" :surname "can"}])


;debuggingimize başlayaşım
(->> my-vec (mapv (comp vec vals)))
;önce makromuzu expand edelim
(macroexpand '(->> my-vec (mapv (comp vec vals))))
;makromuzun expand edilmiş halini debugging etmek çok daha kolaydır, şimdi formlarına ayıralım.
(mapv
  (comp vec vals) my-vec)
;önce dıştaki formun imzasına bakalım map f coll
(def f (comp vec vals))
(def coll my-vec)
(mapv f coll)
;=> [[1 "ali" "veli"] [2 "batu" "can"]]

;şimdi fonksiyonu parçalayalım.
((comp vec vals) {:id 1 :name "ali" :surname "veli"})
;comp transducer degilse soldan sağa doğru çalışırdı.bu nedenle önce verilen mapin valuelarını alır
;sonra vectörün içerisine koyar.
;[1 "ali" "veli"] sonuç bu olur. iterationlardan sonra mapv bütün collectionları vector ile kaplar.