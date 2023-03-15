(ns app.P04-TwoPageApp
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            ))

(e/defn TwoPageApp []
        (e/client
          (let [!state (atom {:url (-> js/window .-location .-pathname) :window "" })]
            ;change :window key to "_blank" if you want open in new window
            (let [url (get (e/watch !state) :url) window (get (e/watch !state) :window)]
              (case url
                "/" (do (dom/h1 (dom/text "MAIN PAGE"))
                        (dom/p (dom/text "To go page 1: ")
                               (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/page1" :target window})
                                      (dom/text "Page 1")))
                        (dom/p (dom/text "To go page 2: ")
                               (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/page2" :target window})
                                      (dom/text "Page 2"))))
                "/page1" (do (dom/h1 (dom/text "Page 1")) (dom/p (dom/text "To go page 2: ") (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/page2" :target window})
                                                                                                    (dom/text "Page 2")))
                             (dom/h1) (dom/p (dom/text "To go page main page: ") (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/" :target window})
                                                                                        (dom/text "Main Page"))))
                "/page2" (do (dom/h1 (dom/text "Page 2")) (dom/p (dom/text "To go page 1: ") (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/page1" :target window})
                                                                                                    (dom/text "Page 1")))
                             (dom/h1) (dom/p (dom/text "To go page main page: ") (dom/a (dom/props {::dom/href "http://0.0.0.0:8080/" :target window})
                                                                                        (dom/text "Main Page"))))
                )
              )
            )
          )
        )
