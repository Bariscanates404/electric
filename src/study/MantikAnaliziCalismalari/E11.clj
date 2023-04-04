(ns study.MantikAnaliziCalismalari.E11)

(def m {1 {:id 1 :name "ali" :surname "veli"}
        2 {:id 2 :name "batu" :surname "can"}})

;-------------------------------------------d06------------------------------------------------

(defn filter-db-by-surname2 [substring db]
  (reduce-kv (fn [m k {:keys [id name surname] :as v}]
               (if (str/includes? (str/lower-case v)
                                  (str/lower-case substring))
                 (assoc m k v)
                 m))
             {} db))

(filter-db-by-surname2 "a" m)
;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

(comment
  ; 1. adım: filter-db-by-surname2 fonksiyonunu genişletelim (içini açalım)
  (def substring "a")
  (def db m)
  (reduce-kv (fn [m k {:keys [id name surname] :as v}]
               (if (str/includes? (str/lower-case v)
                                  (str/lower-case substring))
                 (assoc m k v)
                 m))
             {} db)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

  ; 2. adım: reduce-kv çağrısını hizalayalım temiz bir şekilde
  (reduce-kv
    (fn [m k {:keys [id name surname] :as v}]
      (if
        (str/includes? (str/lower-case v)
                       (str/lower-case substring))
        (assoc m k v)
        m))
    {}
    db)

  ; 3. adım: anonim fonksiyona isim verelim
  (def f
    (fn [init k {:keys [id name surname] :as v}]
      (if
        (str/includes? (str/lower-case v)
                       (str/lower-case substring))
        (assoc init k v)
        init)))

  (identity db)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}
  ; 4. adım: iterasyonları tek tek debug edicez

  ; 1. iterasyon
  (def init {})
  (def k 1)
  (def v (db k))
  (f init k v)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}}

  ; 2. iterasyon
  (def init {1 {:id 1, :name "ali", :surname "veli"}})
  (def k 2)
  (def v (db k))
  (f init k v)
  ;=> {1 {:id 1, :name "ali", :surname "veli"}, 2 {:id 2, :name "batu", :surname "can"}}

  ; end
  ,)

