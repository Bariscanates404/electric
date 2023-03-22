(ns app.P08a-SearchFunction
  (:require
    [clojure.string :as str]
    [hyperfiddle.electric :as e]
    [hyperfiddle.electric-dom2 :as dom]
    [hyperfiddle.electric-ui4 :as ui]))

(def db
  {"a1" "v1"
   "a2" "v2"
   "b1" "v3"})
(defn filter-db [?s] ;Burada filter functionunda bulunan k v sembollerinden k ile ararsak key v ile ararsak
  ;value den arayabiliriz.
  (->> db
       (filter (fn [[k v]] (str/includes? (str/lower-case (str k)) (str/lower-case (str ?s)))))
       (into {})))








(e/defn App []
        (e/client
          (dom/h1 (dom/text "Search"))
          (let [!search (atom "")
                search (e/watch !search)
                dt (filter-db search)]   ;search degiskeni ile atomu izleyerek map içerisinde arama yapıyor




            (e/client ;input elementine girilen degeri alır ve !search atomuna atama yapar.
                (ui/input search (e/fn [v] (reset! !search v))
                          (dom/props {:type "search"}))



                (dom/table   ;tabledaki bilgiyi değiştiren kısım
                  (e/for-by first [[k v] dt]
                            (dom/tr
                              (dom/td (dom/text k))
                              (dom/td (dom/props {:style {:white-space :nowrap}}) (dom/text v)))))))))



