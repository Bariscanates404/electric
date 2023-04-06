(ns study.MantikAnaliziCalismalari.E20)



(def my-vec [1 {:id 1 :name "ali" :surname "veli"}
             2 {:id 2 :name "batu" :surname "can"}])



(def my-set (into (sorted-set-by #(apply compare (map :surname %&)))
                  (take-nth 2 (rest my-vec))))

(first (subseq my-set >= {:name "a":surname "a"}))
