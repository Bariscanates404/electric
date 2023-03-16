(ns app.P04b-TwoPageApp2
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            ))

(e/defn TwoPageApp []
        (e/client
          (case (-> js/window .-location .-pathname)
            "/" (do (dom/h1 (dom/text "MAIN PAGE"))
                    (dom/p (dom/text "To go page 1: ")
                           (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/page1"})
                                  (dom/text "Page 1")))
                    (dom/p (dom/text "To go page 2: ")
                           (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/page2"})
                                  (dom/text "Page 2"))))
            "/page1" (do (dom/h1 (dom/text "Page 1")) (dom/p (dom/text "To go page 2: ") (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/page2"})
                                                                                                (dom/text "Page 2")))
                         (dom/h1) (dom/p (dom/text "To go page main page: ") (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/"})
                                                                                    (dom/text "Main Page"))))
            "/page2" (do (dom/h1 (dom/text "Page 2")) (dom/p (dom/text "To go page 1: ") (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/page1"})
                                                                                                (dom/text "Page 1")))
                         (dom/h1) (dom/p (dom/text "To go page main page: ") (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/"})
                                                                                    (dom/text "Main Page"))))
            )
          )
        )
