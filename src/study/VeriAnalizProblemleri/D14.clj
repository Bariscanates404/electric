(ns study.VeriAnalizProblemleri.D14)
;Debugging ref ->
;rfr: src/study/MantikAnaliziCalismalari/E28.clj

;Girdi:
;
;```clojure
;{1 {:id 1
;    :name {:first "ali" :last "veli"}}
; 2 {:id 2
;    :name {:first "batu" :last "can"}}}
;```
;
;Çıktı:
;
;```clojure
;{1 {:id 1
;  :name {:first "ali" :last "veli" :fullname "ali veli"}}
; 2 {:id 2
;  :name {:first "batu" :last "can" :fullname "batu can"}}}
;```


(def !my-map (atom {1 {:id   1
                       :name {:first "ali" :last "veli"}}
                    2 {:id   2
                       :name {:first "batu" :last "can"}}}))
;expected output:
;        {1 {:id 1
;  :name {:first "ali" :last "veli" :fullname "ali veli"}}
;         2 {:id 2
;  :name {:first "batu" :last "can" :fullname "batu can"}}}

(def my-map {1 {:id   1
                 :name {:first "ali" :last "veli"}}
              2 {:id   2
                 :name {:first "batu" :last "can"}}})

(swap! !my-map update-vals #(assoc-in % [:name :fullname]
                                      ;burada map in seviyelerinde dolaşabiliyoruz, :name key inin valuesuna sonra :fullname keyini
                                      ;oluşturuyoruz sonra aşağıdaki fonksiyonun sonucunu value olarak atıyoruz.
                                      (str (-> % :name :first)
                                           " "
                                           (-> % :name :last))))
;update vals kendisi otomatik olarak nested maplardaki en üst seviyedeki ilk valueyu döndürür ve içerisinde arguman
;olarak verilen fonksiyonu kullanarak değişiklikler yapar.
;

(update-vals my-map #(assoc-in % [:name :fullname]
                                      (str (-> % :name :first)
                                           " "
                                           (-> % :name :last))))
;atom olmayan bir map ile aynı işlemi yapalım.


(swap! !my-map update-vals #(assoc-in % [:pass]
                                      ;burada update-vals mapin ilk keyi olan 1 in value değerine giriyor
                                      ;devamında  biz :pass keyine gir dedik. bu key olmadığı için oluşturuyoruz ve
                                      ;aşağıdaki fonksiyonun sonucunu ona atıyoruz.
                                      (str (-> % :name :first)
                                           " "
                                           (-> % :name :last))))

;{1 {:id 1, :name {:first "ali", :last "veli", :fullname "ali veli"}, :pass "ali veli"},





;başka bir yöntem.
(defn attach-full-name [{{:keys [first last]} :name
                         :as data}]
  (update data :name assoc :full (str first " " last)))


(swap! !my-map update-vals attach-full-name)

;; {1 {:id 1, :name {:first "ali", :last "veli", :full "ali veli"}},
;;  2 {:id 2, :name {:first "batu", :last "can", :full "batu can"}}}


;farklı bir deneme ama bana karışık geliyor. yukarıdaki çözümler daha net ve anlaışabilir.
(swap! !my-map update-vals
       (fn [v]
         (update v :name
                 (fn [n]
                   (let [{:keys [first last]} n]
                     (assoc n :fullname (str first " " last))
                     )
                   )
                 )
         )
       )