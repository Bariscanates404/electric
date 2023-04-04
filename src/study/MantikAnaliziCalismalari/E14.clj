(ns study.MantikAnaliziCalismalari.E14)

(comment
  (defn flatten-one-level [coll]
    (mapcat #(if (sequential? %) % [%]) coll))
  ;map fonksiyon ve  coll alıyor.
  (def f #(if (sequential? %) % [%]))
  ;burada anonim fonksiyon verilen argümanı alıyor ve squential olup olmadığını kontrol
  ;ediyor. Eğer sequential ise direk olarak kendisini geri döndürüyor değil ise
  ;vektör içerisine koyarak döndürüyor.
  (def coll [[1 2 [4 5 [6]] [3]]])
  (mapcat)
  ;mapcat apply concat ile map in birleşimidir.
  (map f coll)
  ;=> (1 2 [4 5 [6]] [3]) eğer apply concat kullanmadan map kullanırsak gördüğümüz gibi istediğimiz
  ;sonuca ulaşamıyoruz.
  (apply concat (map f coll))
  ;=> ([1 2 [4 5 [6]] [3]])
  )

