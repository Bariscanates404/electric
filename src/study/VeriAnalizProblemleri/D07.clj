(ns study.VeriAnalizProblemleri.D07
  (:require [study.reusable-functions :as rf]
            [clojure.string :as str]))




;Bu problemde bir girdi veri var, bir de bir arama kriteri var.
;
;Girdi:
;
;```clojure
;[1 {:id 1 :name "ali" :surname "veli"}
; 2 {:id 2 :name "batu" :surname "can"}]
;```
;
;Arama kriteri:
;
;```
;"ca"
;```
;
;Bu anahtar kelimenin geçtiği `surname` property'sine sahip objeleri bulun.

(def my-vec [1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}])

(defn destructure-fn [x]
  (let [{:keys [id name surname]} x]
    (let [i id
          k name
          v surname]
      (if (str/includes? (str/lower-case (str v)) (str/lower-case (str "ca")))
        (str/lower-case (str v))
        (str/lower-case "NO!"))
      )))


(for [len (range 0 (count my-vec))]
   (if (map? (my-vec len))
     (destructure-fn (my-vec len))
     (str "no")
     )
   )
;=> ("no" "no!" "no" "can")



(my-vec 3)
;=> {:id 2, :name "batu", :surname "can"}

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

(filter-db-by-surname "ca" my-vec 3)
;=> ([:id 2] [:name "batu"] [:surname "can"])


; expected output: [2 {:id 2 :name "batu" :surname "can"}]  <<<<---------------------

(def my-set (into (sorted-set-by #(apply compare (map :surname %&)))
                  (take-nth 2 (rest my-vec))))


(defn destructure-result-and-put-into-vector [x]
  (let [{:keys [id name surname]} x]
    (let [i id]
      (into [] {id x})
      )))
(first (subseq my-set >= {:name "a":surname "a"}))

(first(destructure-result-and-put-into-vector (first (subseq my-set >= {:surname "a"}))))
;=> [2 {:id 2, :name "batu", :surname "can"}]