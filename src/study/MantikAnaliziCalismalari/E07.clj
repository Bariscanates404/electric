(ns study.MantikAnaliziCalismalari.E07)

(comment
  ;öncelikle into [to form] formunda kullanılmış buna göre debuggingi mizi yapalım.
  (into {}
        (remove
          (comp nil? second)
          m))

  ((comp nil? second) {:a 1 :b 2 :c nil})
  ;=> false
  )
