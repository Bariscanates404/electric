(ns app.P06-TableAppByUsingVectorAndFor
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]))

(def students
  [{:name "Felix" :grade "5"}
   {:name "Villow" :grade "10"}
   {:name "Lana" :grade "8"}
   {:name "Denis" :grade "7"}
   ])

(e/defn VectorTable []
        (e/client

          (dom/table
            (dom/tr
              (dom/th (dom/text "Name"))
              (dom/th (dom/text "Grade")))
            (e/for [{:keys [name grade]} students]
                   (dom/tr
                     (dom/td (dom/text name))
                     (dom/td (dom/text grade))))
            )
          )
        )
