(ns study.MantikAnaliziCalismalari.E12)


(defn filter-db-by-surname [substring db]
  (into {}
        (filter (fn [[_ {:keys [id name surname] :as v}]]
                  (str/includes? (str/lower-case v)
                                 (str/lower-case substring))))
        db))

(filter-db-by-surname "a" m)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

(comment
  ; 1. adım: filter-db-by-surname fonksiyonunu genişletelim (içini açalım)
  (def substring "a")
  (def db m)
  (identity db)
  (into
    {}
    (filter (fn [[_ {:keys [id name surname] :as v}]]
              (str/includes? (str/lower-case v)
                             (str/lower-case substring))))
    db)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

  ; 2. adım: filter fonksiyonunu debug edelim
  ; hizalayalım argümanları ayrı ayrı
  (filter
    (fn [[_ {:keys [id name surname] :as v}]]
      (str/includes? (str/lower-case v)
                     (str/lower-case substring))))

  ; 3. adım: anonim fonksiyona pred ismi verelim
  (def pred
    (fn [[_ {:keys [id name surname] :as v}]]
      (str/includes? (str/lower-case v)
                     (str/lower-case substring))))
  ; 4. adım: pred fonksiyonunun içindeki anonim fonksiyonu kaldırabiliriz
  (defn pred [[_ {:keys [id name surname] :as v}]]
    (str/includes? (str/lower-case v)
                   (str/lower-case substring)))

  (filter pred db)
  (def coll db)
  (identity coll)
  ; 5. adım: filter çağrısının iterasyonlarına parçalayalım
  ; 1. iterasyon
  (first coll)
  ;=> [1 {:id 1, :name "ali", :surname "veli"}]
  (pred (first coll))
  ;=> true

  ; 2. iterasyon
  (first (rest coll))
  ;=> [2 {:id 2, :name "batu", :surname "can"}]
  (pred (first (rest coll)))
  ;=> true

  ; end
  ,)
