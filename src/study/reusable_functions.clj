(ns study.reusable-functions)



;derin nested map yada vectorleri tamamen düzleştirmek için bir method.
;input
;(def sample [{:top {:top {:top [:bottom {:top {:top [:bottom :bottom :bottom]}} :bottom :bottom :bottom]}}},
;                        {:top {:top [:bottom :bottom :bottom]}},
;                        {:top [:bottom :bottom]}])
;output
;(:top :top :top :bottom :top :top :bottom :bottom :bottom
; :bottom :bottom :bottom :top :top :bottom :bottom
; :bottom :top :bottom :bottom)
(defn flat-a-coll [coll]
  (if (coll? coll)
    (mapcat flat-a-coll coll)
    [coll]))

;derin bir nested collectionu tamamen düzleştirir ve sadece keywordleri döner.
;(:top :top :top :bottom :top :top :bottom :bottom
; :bottom :bottom :bottom :bottom :top :top :bottom
; :bottom :bottom :top :bottom :bottom)
(defn flatten-and-get-keywords [coll]
  (filter keyword? (tree-seq coll? seq coll))
  )


;verilen coll u bir seviye düzleştirir.
(defn flatten-one-level [coll]
  (mapcat
    #(if (sequential? %) % [%])
    coll))

;verilen collu iki seviye düzleştirir.
(defn flatten-one-level-2 [coll]
  (->> coll
       (mapcat
         #(if
            (sequential? %)
            %
            [%]))))

; en dış koleksiyonu vector'e dönüştürür.
(defn transform-outer-coll-to-vector [coll]
  (apply vector coll)
  )

; en dış koleksiyonu map'e dönüştürür.
(defn transform-outer-coll-to-map [coll]
  (apply hash-map coll)
  )

; Verilen vectorun içerisindeki elementleri sıra sıra gezer ve biz döndürür.
(defn walk-inside-a-vector-elements [coll]
  (for [lenght (range 0 (count coll))]
    (get coll lenght)
    )
  )