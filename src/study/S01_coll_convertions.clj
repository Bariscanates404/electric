(ns study.S01-coll-convertions)

;collectionları birbirlerine kolayca dönüştürmek

(into [] '(1 2 3 4)) ; ==> [1 2 3 4]
(into #{} [1 2 3 4]) ; ==> #{1 2 3 4}
(into {} #{[1 2] [3 4]}) ; ==> {3 4, 1 2}
(into #{} #{[1 2] [3 4]}) ; ==> #{[3 4] [1 2]}
(into #{} [{1 2} {3 4}]) ; ==> #{{1 2} {3 4}}


;------------------------------tek katmanlı vectorler------------------------------
(def vec1 [1 2 3 4])
(into #{} vec1) ; ==> => #{1 4 3 2} dış tarafta olan bir katmanı siliyor ve içini mapa koyuyoruz
; (into {} vec1) ; ==> hata veriyor => Don't know how to create ISeq

;------------------------------iki katmanlı vectorler------------------------------
(def vec2 [[1 2][3 4]])
(into #{} vec2) ; ==> => #{[3 4] [1 2]}
(into {} vec2) ; ==> => {1 2, 3 4}

;------------------------------üç katmanlı vectorler------------------------------
(def vec3 [[1 2 [5 6]][3 4[7 8]]])
(into #{} vec3) ; ==> => #{[3 4 [7 8]] [1 2 [5 6]]} en dış taraftaki katmanı sildik tekrardan ve mapa koyduk
; (into {} vec3) ; ==> hata veriyor => Vector arg to map conj must be a pair

;-----------------------seq ile map ve vectörler içerisinde dolaşmak------------------------------
(seq vec1) ; ==> => (1 2 3 4)
(seq vec2) ; ==> => ([1 2] [3 4])
(seq vec3) ; ==> => ([1 2 [5 6]] [3 4 [7 8]])
;seq en dıştaki  vector katlamanını siler ve kalan bütün veriyi list içerisine koyar.
(def map1 {:foo 1 :bar 2 :baz 3})
(def map2 {:foo 1 :bar {:baz 3}})
(def map3 {:foo 1 :bar {:baz 3 :dax {:can 9}}})

(into [] map1)

(seq map1) ; ==> => ([:foo 1] [:bar 2] [:baz 3])
(seq map2) ; ==> => ([:foo 1] [:bar {:baz 3}])
(seq map3) ; ==> => ([:foo 1] [:bar {:baz 3, :dax {:can 9}}])

;-----------------------for ile maplari dolaşlak------------------------------
(for [[k v] map1] ; ==> => (":foo 1" ":bar 2" ":baz 3")
  (pr-str k v))

(for [[k v] map2] ; ==> => (":foo 1" ":bar {:baz 3}")
  (pr-str k v))

(for [[k v] map2] ; ==> => (":foo 1" ":bar {:baz 3}")
  (pr-str k v))


(for [[k v] map3] ; ==> => (":foo 1" ":bar 2" ":baz 3")
  (pr-str k v))