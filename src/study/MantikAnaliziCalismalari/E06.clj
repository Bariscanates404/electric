(ns study.MantikAnaliziCalismalari.E06)

(comment

  (defn remove-nils
    [m]
    (let [f (fn [[k v]] (when v [k v]))]
      (postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))

  ;codumuzda iki temel form var, şimdi önce coll formunu sonrada let binding formunu inceleyelim.
  (let [f (fn [[k v]] (when v [k v]))])
  ;postwalk derin collectionlarda bütün ögeleri tek tek gezen bir fonksiyon
  ;içeride tanımladığımız anonim fonksiyon ise  postwalking gezdiği her bir öğeyi argüman olarak alıp
  ;fpnksiyon içerisinde işleme sokar.
  (postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) {1 {:id 1 :name "ali" :surname "veli"}
                                                          2 {:id 2 :name "batu" :surname "can"}})
  )

