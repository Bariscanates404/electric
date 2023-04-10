(ns study.MantikAnaliziCalismalari.E04)


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


  (def k :a)
  (def v :1)
  ;şimdi for loop'u muzu şu şekilde yazabiliriz.
  (for [[k v] given-map
        :when
        true] {k v})
;1. iteration {:a 1}
(def k :b)
(def v :2)
;şimdi for loop'u muzu şu şekilde yazabiliriz.
(for [[k v] given-map
      :when
      true] {k v})
;1. iteration {:b 2}

(def k :c)
(def v nil)
;şimdi for loop'u muzu şu şekilde yazabiliriz.
(for [[k v] given-map
      :when
      false] {k v})
;1. iteration {}
;sonuç olarak ({:a 1} {:b 2}) bu dönecek.

