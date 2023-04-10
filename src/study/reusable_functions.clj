(ns study.reusable-functions)
(use 'clojure.walk)




;derin nested map yada vectorleri tamamen düzleştirmek için bir method.
;input
;(def sample [{:top {:top {:top "merhaba"}}}])
;output
;=> (:top :top :top "merhaba")
(defn flat-a-coll [coll]
  (if (coll? coll)
    (mapcat flat-a-coll coll)
    [coll]))

(comment
  (def sample [{:top {:top {:top "merhaba"}}}])
  (flat-a-coll sample)
  ;=> (:top :top :top "merhaba")
  )

;derin bir nested collectionu tamamen düzleştirir ve sadece keywordleri döner.
;(def sample [{:top {:top {:top "merhaba"}}}])
;=> (:top :top :top)
(defn flatten-and-get-keywords [coll]
  (filter keyword? (tree-seq coll? seq coll))
  )

(comment
  (def sample [{:top {:top {:top "merhaba"}}}])
  (flatten-and-get-keywords sample)
  ;=> (:top :top :top)
  )


;verilen coll'u bir seviye düzleştirir.
(defn flatten-one-level [coll]
  (mapcat
    #(if (sequential? %) % [%])
    coll))


; en dış koleksiyonu vector'e dönüştürür.
(defn transform-to-vector [coll]
  (apply vector coll)
  )

; en dış koleksiyonu map'e dönüştürür.
(defn transform-to-map [coll]
  (apply hash-map coll)
  )

; Verilen vectorun içerisindeki elementleri sıra sıra gezer ve bize döndürür.
;input    (def my-vec [["name" "ali"] ["name" "batu"] "surname" "veli" "surname" "can"])
;output   => (["name" "ali"] ["name" "batu"] "surname" "veli" "surname" "can")
(defn walk-inside-a-vector-elements [coll]
  (for [len (range 0 (count coll))]
    (get coll len)
    )
  )
(comment
  (def my-vec [["name" "ali"] ["name" "batu"] "surname" "veli" "surname" "can"])
  (walk-inside-a-vector-elements my-vec )
  )


;input => [{"name" "ali", "surname" "veli"} {"name" "batu", "surname" "can"}]
;output =>[{:name "ali", :surname "veli"} {:name "batu", :surname "can"}]
;Verilen coll un içerisinde recursivly olarak döner ve bütün keyleri keyworde dönüştürür.
(defn keywordize-keys-1
  "Recursively transforms all map keys from strings to keywords."
  [m]
  (let [f (fn [[k v]] (if (string? k) [(keyword k) v] [k v]))]
    (clojure.walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))


;input =>  [{:name "ali", :surname "veli"} {:name "batu", :surname "can"}]
;output => [{"name" "ali", "surname" "veli"} {"name" "batu", "surname" "can"}]
;Verilen coll un içerisinde recursivly olarak döner ve bütün keyleri stringe dönüştürür.
(defn stringify-keys-1
  "Recursively transforms all map keys from keywords to strings."
  [m]
  (let [f (fn [[k v]] (if (keyword? k) [(name k) v] [k v]))]
    (clojure.walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))


;input: (def vec [1 [2 3 4] 5 6 7 9])
;(vec-remove-element 1 vec)
;output:=> [1 5 6 7 9]
;verilen vectorün verilen pos daki elemanini siler ve aynı sıralamada kalan elementlerden oluşan vectorü geri döndürür.
(defn vec-remove-element
  "remove elem in coll"
  [pos coll]
  (into (subvec coll 0 pos) (subvec coll (inc pos))))


;(use 'clojure.walk)
;verdiğiniz nested map'i içeriğini tamamen aynı şekilde koruyarak butün mapleri vectore dönüştürür.
;input--->   {1 {:id 1 :x [:name "ali"] :surname "veli"} 2 {:id 2 :name "batu" :surname "can"}}
;output==>   [1 [:id 1 :x [:name "ali"] :surname "veli"] 2 [:id 2 :name "batu" :surname "can"]]
(defn transform-maps-to-vector [map]
  (postwalk (fn [x]
              (if (map? x) (into [] cat x) x))
            map)
  )

;Imperative style for loop
;input (for-loop [i 0 (< i 4) (inc i)]
;  (println i))
;output ==>
;;0
;;1
;;2
;;3
;; ==> nil
(defmacro for-loop-imperative-style [[sym init check change :as params] & steps]
  `(loop [~sym ~init value# nil]
     (if ~check
       (let [new-value# (do ~@steps)]
         (recur ~change new-value#))
       value#)))