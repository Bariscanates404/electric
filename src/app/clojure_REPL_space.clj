(ns app.clojure-REPL-space
  (:require [clojure.string :as str]))




(comment
  (let [!db (atom [])]
    (swap! !db conj {"username" "password"})
    (swap! !db conj {"username" "password"})
    (swap! !db conj {"username" "password"})
    (swap! !db conj {"username" "password"})

    )
  ,)


(comment
  (def db2 [[:q "w"] [:w "e"] [:e "r"]])
  (defn filter-db [?s]
    (->> db2
         (filter
           (fn
             [{:keys [name surname]}]
             (let [k name
                   v surname]
               (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s))))))))

  (filter-db :q)
  (identity db2)
  (identity db)
  ;=> ()


  ,)

(comment
  (def list-vector-db1 ([1 {:name "q", :surname "w"}] [2 {:name "a", :surname "s"}] [3 {:name "z", :surname "x"}]))

  (def mapdb1 {1 {:name "q", :surname "w"}, 2 {:name "a", :surname "s"}, 3 {:name "z", :surname "x"}})

  (defn filter-db [?s]
    (->> (for [id (range 1 4)]
           (mapdb1 id))
         (filter
           (fn
             [{:keys [name surname]}]
             (let [k name
                   v surname]
               (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s))))))))
  (mapdb1 1)
  (filter-db "a")

  (for [id (range 1 4)]
    (mapdb1 id))

  (identity mapdb1)

  (defn filter-db2 [?s]
    (->> (for [id (range 1 4)]
           (mapdb1 id))
           (fn
             [{:keys [name surname]}]
             (let [k name
                   v surname]
               (str k ":" v)))))

  (filter-db2 "s")

  ,)

(comment


  ;({:name "a", :surname "s"} {:name "s", :surname "d"} {:name "d", :surname "f"})

  (defn new-map [map table-id]
    (for [id (range 1 table-id)]
      (map id)))

  (new-map mapdb1 5)
  ;=> ({:name "q", :surname "w"} {:name "a", :surname "s"} {:name "z", :surname "x"} nil)


  ,)