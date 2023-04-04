(ns study.MantikAnaliziCalismalari.E04)

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

  )
