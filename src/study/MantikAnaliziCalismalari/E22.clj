(ns study.MantikAnaliziCalismalari.E22)


(defn filter-vector-func-by-postwalk [coll search-str]
  (->> coll
       (clojure.walk/postwalk
         (fn [form]
           (let [form (if (vector? form)
                        (vec (keep identity form))
                        form)]
             (cond
               (and (vector? form)
                    (every? vector? form)) form
               (and (vector? form)
                    (not (every? string? form))) nil
               (and (vector? form)
                    (every? string? form)
                    (->> form
                         (some (fn [str-vec]
                                 (-> str-vec
                                     clojure.string/lower-case
                                     (clojure.string/includes? search-str)))))) form
               :else form))))
       (keep identity)
       vec))
