(ns study.VeriAnalizProblemleri.D07c-volatile!-approach)



(def my-vec [
             1 {:id 1 :name "ali"  :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}
             3 {:id 3 :name "alz"  :surname "can"}
             4 {:id 4 :name "alz"  :surname "not"}
             ])

(defn idx->meta [pair-col]
  (->> pair-col
       (apply hash-map)
       (reduce-kv (fn [acc k v]
                    (let [idx       k
                          str-vals  (filterv string? (vals v))
                          str-paths (->> str-vals
                                         (map #(clojure.string/split % #"")))]
                      (->> str-paths
                           (reduce (fn [acc str-path]
                                     (update-in acc str-path (fnil conj []) v))
                                   acc))))
                  {})
       )
  )

#_(idx->meta my-vec)

(defn search [trie query]
  (let [query-path (clojure.string/split query #"")
        query-path-partials (->>
                              (count query-path)
                              inc
                              (range 1)
                              (map #(take % query-path)))
        ]
    (->> query-path-partials
         (map (fn [path]
                [path (get-in trie path)])))))

#_(search (idx->meta my-vec) "ca")

(defn search-vals [search-val]
  (let [acc (volatile! #{})]
    (->>
      search-val
      (map second)
      (clojure.walk/postwalk
        (fn [form]
          (when (and (vector? form) (every? map? form))
            (vswap! acc (partial apply conj) form))
          form)))
    (->> @acc)))

#_(->>
    (search (idx->meta my-vec) "ca")
    (search-vals))

;; #{{:id 2, :name "batu", :surname "can"}
;;   {:id 3, :name "alz", :surname "can"}}