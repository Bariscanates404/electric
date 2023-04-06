(ns study.MantikAnaliziCalismalari.E21
  (:require [clojure.string :as str]))

(def my-vec [1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}])




(defn filter-db-by-surname [?s db id]
  (->> (db id)                                              ;=> {:id 2, :name "batu", :surname "can"}
       (filter
         (fn
           [{:keys [id name surname]}]
           (let [i id
                 k name
                 v surname]
             (if (str/includes? (str/lower-case (str v)) (str/lower-case (str ?s)))
               (str/lower-case (str v))
               (str/lower-case "NO!"))
             )))
       (into {})))

(filter-db-by-surname "a" my-vec 3)

(macroexpand '(->> (db id)                                              ;=> {:id 2, :name "batu", :surname "can"}
                  (filter
                    (fn
                      [{:keys [id name surname]}]
                      (let [i id
                            k name
                            v surname]
                        (if (str/includes? (str/lower-case (str v)) (str/lower-case (str ?s)))
                          (str/lower-case (str v))
                          (str/lower-case "NO!"))
                        )))
                  (into {})))

(into
  {}
  (filter
    (fn
      [{:keys [id name surname]}]
      (let
        [i id k name v surname]
        (if
          (str/includes? (str/lower-case (str v)) (str/lower-case (str ?s)))
          (str/lower-case (str v))
          (str/lower-case "NO!"))))
    (db id)))