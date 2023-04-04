(ns study.MantikAnaliziCalismalari.E09)

(comment
  (def my-vec [-1 -2 -3 -4 5 6])
  (defn index-of-pred
    [pred coll]
    (ffirst
      (filter
        (comp pred second)
        (map-indexed list coll)
        )))

  ;bu fonksiyon bir vector içerisinde bulunan ilk pozitif değeri döndürür
  ;şimdi formlarına ayırıp nasıl çalıştığını görelim.


  (def coll (map-indexed list my-vec))
  ;=> ((0 -1) (1 -2) (2 -3) (3 -4) (4 5) (5 6))

  )
