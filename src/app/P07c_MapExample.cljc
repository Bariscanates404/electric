(ns app.P07c-MapExample
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            ))

(def students
  {:person/id {1 {:person/id 1 :person/name "Felix" :person/grade "5"}
               2 {:person/id 2 :person/name "Villow" :person/grade "10"}
               3 {:person/id 3 :person/name "Lana" :person/grade "8"}
               4 {:person/id 4 :person/name "Denis" :person/grade "7"}
               }})


(e/defn TableAppMultiByMap []
        (e/client
          (dom/table
            (dom/props {:class "hyperfiddle"})
            (e/client
              (dom/table
                (dom/tr
                  (dom/th (dom/text "Id"))
                  (dom/th (dom/text "Name"))
                  (dom/th (dom/text "Surname"))))
                (dom/tr
                  (dom/td (dom/text "1"))
                  (dom/td (dom/text "person/name"))
                  (dom/td (dom/text "person/surname"))))
              )
            )
          )




