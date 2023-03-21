(ns app.P06b-TableAppByUsingMapAndFor
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

(def students
  {:person/id {1 {:person/id 1 :person/:name "Felix" :person/:grade "5"}
               2 {:person/id 2 :person/:name "Villow" :person/:grade "10"}
               3 {:person/id 3 :person/:name "Lana" :person/:grade "8"}
               4 {:person/id 4 :person/:name "Denis" :person/:grade "7"}
               }})

(def ids (keys (:person students)))

(defn get-person-by-students-id "get person by :person id value" [id]
  (get
    (students :person)
    id)
  )


(e/defn MapTable []
        (e/client
          (dom/table
            (dom/tr
              (dom/th (dom/text "Name"))
              (dom/th (dom/text "Grade")))
            (e/for [{:keys [name grade]} (map get-person-by-students-id ids)]
                   (dom/tr
                     (dom/td (dom/text name))
                     (dom/td (dom/text grade))))
            )
          )
        )
