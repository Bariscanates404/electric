(ns study.MantikAnaliziCalismalari.E28)


;date: 20230417
;rfr: src/study/VeriAnalizProblemleri/D14.clj


(def m {1 {:id   1
           :name {:first "ali" :last "veli"}}
        2 {:id   2
           :name {:first "batu" :last "can"}}})

(update-vals m #(assoc-in % [:name :fullname]
                          (str (-> % :name :first)
                               " "
                               (-> % :name :last))))

(defn f1 [a]
  (assoc-in a [:name :fullname]
            (str (-> a :name :first)
                 " "
                 (-> a :name :last))))



;-------------------------------------------DEBUGGING------------------------------------------------
;Bu debuggingi yaparken içeriden dışarıya doğru debug edeceğim. Öncelikle en içteki fonksiyonumuzdan başlayalım.
(comment

  ;(str (-> % :name :first)
  ;     " "
  ;     (-> % :name :last))

  (-> (m 1) :name :first)
  ;=> "ali"

  (-> (m 1) :name :last)
  ;=> "veli"

  (str "ali"
       " "
       "veli")
  ;=> "ali veli"
  (str "baris"
       " "
       "can")
  ;=> "baris can"

  ;iç fonksiyonun debuggingi bitti, gördüğümüz gibi nested mapimizin içerisinde bulunan first ve last anahtarının değerlerini
  ;bize döndürüyor ve str fonksiyonu ile birleştiriyor.


  ;-------------------------------------------update-vals-f debug edelim------------------------------------------------
  (f1 {:id 1 :name {:first "ali" :last "veli"}})
  ;=> {:id 1, :name {:first "ali", :last "veli", :fullname "ali veli"}}
  (f1 {:id 2 :name {:first "batu" :last "can"}})
  ;=> {:id 2, :name {:first "batu", :last "can", :fullname "batu can"}}

  (update-vals m f1)
  ;=>
  ;{1 {:id 1, :name {:first "ali", :last "veli", :fullname "ali veli"}},
  ; 2 {:id 2, :name {:first "batu", :last "can", :fullname "batu can"}}}
  )