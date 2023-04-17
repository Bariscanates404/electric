(ns study.MantikAnaliziCalismalari.AnonFoncNaming)



;örnek anonim fonk isimlendirmeleri ==>
(def f1 #(assoc-in % [:name :fullname]
                   (str (-> % :name :first)
                        " "
                        (-> % :name :last))))
(defn f2 [a]
  (assoc-in a [:name :fullname]
            (str (-> a :name :first)
                 " "
                 (-> a :name :last))))
