(ns study.MantikAnaliziCalismalari.E08)

(comment
  (defn seq->map
    [s]
    (let [s+ (concat s [nil])]
      (zipmap
        (take-nth 2 s+)
        (take-nth 2 (rest s+)))))

  (seq->map '(1 2 3 4 5 6 7 8 9 10 11 12 13 14 15))

  ((concat s [nil]))

  )
